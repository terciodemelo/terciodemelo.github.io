(ns parses.clojure
  (:gen-class)
  (:use [hiccup.core])
  (:require [clojure.string :refer [blank?]]))

(def initial-parsing-state {:type-name  :span.symbol :is-escaped false})

(def reserved-symbols-1 #{"def" "defn" "let" "loop" "if" "do" "go" "fn" "with-open"
                          "and" "or" "not" "->" "->>" "ns" "doseq"})
(def reserved-symbols-2 #{"filter" "println" "map" "pmap" "reduce" "str" "comment"
                          "contains?" "resolve" "require" "set"})


(defn token-html [type-name content]
  (html
   (if (= type-name :span.symbol)
     (cond
       (contains? reserved-symbols-1 content) [type-name {:reserved "1"} content]
       (contains? reserved-symbols-2 content) [type-name {:reserved "2"} content]
       :else                                  [type-name content])
     [type-name content])))

(defn to-html [code]
  (loop [index 0
         output-stack nil
         token-stack nil
         parsing-state initial-parsing-state]
    
    (let [current-char             (if (< index (.length code))
                                     (.charAt code index))
          current-str              (str current-char)
          next                     (+ index 1)
          type-name                (:type-name parsing-state)
          in-string                (:in-string parsing-state)
          in-keyword               (= (:type-name parsing-state) :span.keyword)
          is-escaped               (:is-escaped parsing-state)
          in-paren                 (= type-name :span.paren)
          is-symbol                (= type-name :span.symbol)
          is-blank                 (blank? current-str)
          empty-token-stack        (nil? token-stack)

          reset-type-state         #(assoc parsing-state :type-name :span.symbol)
          resolved-token-stack     #(apply str (reverse token-stack))
          resolved-output-stack    #(apply str (reverse output-stack))
          flushed-token-stack      #(if (not empty-token-stack)
                                      (conj output-stack (token-html type-name (resolved-token-stack)))
                                      output-stack)
          flushed-current-str      #(conj output-stack current-str)
          feed-token-stack         #(conj token-stack current-str)
          finished-code            (>= index (.length code))
          finished-token           (and is-blank
                                        (not finished-code)
                                        (not in-string)
                                        (not is-escaped))
          keyword-start            (and (= current-str ":") (not in-string) (not in-keyword))
          parenthesis-like         (and (.contains "'#(){}[]" current-str)
                                        (not is-escaped)
                                        (not in-string))
          string-delimiter         (and (= current-str "\"") (not is-escaped))]
      (cond
        (:force-flush parsing-state)
        (recur next (flushed-token-stack) (list current-str) initial-parsing-state)
        
        (and finished-code empty-token-stack)
        (resolved-output-stack)

        finished-code
        (recur index (flushed-token-stack) nil parsing-state)

        
        (and is-blank (not in-string) (not empty-token-stack))
        (recur index (flushed-token-stack) nil initial-parsing-state)

        (and is-blank (not in-string))
        (recur next (flushed-current-str) nil initial-parsing-state)

        (and keyword-start (not empty-token-stack))
        (recur next (flushed-token-stack) (list current-str) (assoc parsing-state
                                                                    :type-name :span.keyword))
        
        keyword-start
        (recur next output-stack (list current-str) (assoc parsing-state :type-name :span.keyword))

        (and parenthesis-like in-paren)
        (recur next output-stack (feed-token-stack) parsing-state)
        
        (and parenthesis-like (not empty-token-stack))
        (recur next (flushed-token-stack) (list current-str) (assoc parsing-state :type-name :span.paren))

        parenthesis-like
        (recur next output-stack (list current-str) (assoc parsing-state :type-name :span.paren))

        (and string-delimiter (not in-string))
        (recur next (flushed-token-stack) (list current-str) (assoc parsing-state
                                                                    :type-name :span.string
                                                                    :in-string true))

        string-delimiter
        (recur next output-stack (feed-token-stack) (assoc parsing-state :in-string false))

        in-paren
        (recur next (flushed-token-stack) (list current-str) initial-parsing-state)

        (and is-symbol (= current-str "/"))
        (recur index output-stack token-stack (assoc parsing-state
                                                     :force-flush true
                                                     :type-name :span.namespace))
        
        :else
        (recur next output-stack (feed-token-stack) (assoc parsing-state :is-escaped false))))))
