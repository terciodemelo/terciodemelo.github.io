(ns parses.clojure-test
  (:require [clojure.test :refer :all]
            [parses.clojure :refer :all]))


(defn span-prefix [class]
  (str "<span class=\"" class "\">"))

(def paren-prefix     (span-prefix "paren"))
(def keyword-prefix   (span-prefix "keyword"))
(def symbol-prefix    (span-prefix "symbol"))
(def string-prefix    (span-prefix "string"))
(def namespace-prefix (span-prefix "namespace"))
(def span-suffix      "</span>")

(deftest parenthesis-like-tokenization
  (testing "Simple parenthesis-like tokenization"
    (let [paren-prefix "<span class=\"paren\">"
          paren-suffix "</span>"]
      (doseq [paren "()[]{}"]
        (is (= (str paren-prefix paren paren-suffix)
               (to-html (str paren))))))))

(deftest keyword-tokenization
  (testing "Testing keywords tokenization"
    (doseq [qword [":foo" ":bar" ":foo-bar" ":foo/bar" "::foo-bar"]]
      (is (= (str keyword-prefix qword span-suffix)
             (to-html qword))))))

(deftest blankspaces-preservation
  (testing "Blank spaces should be preserved in the output"
    (do
      (is (= (str paren-prefix "(" span-suffix "   " paren-prefix ")" span-suffix)
             (to-html "(   )")))
      (is (= (str paren-prefix "(" span-suffix " \n " paren-prefix ")" span-suffix)
             (to-html "( \n )")))
      (is (= (str paren-prefix "(" span-suffix " \t " paren-prefix ")" span-suffix)
             (to-html "( \t )")))
      (is (= (str paren-prefix "(" span-suffix " \r " paren-prefix ")" span-suffix)
             (to-html "( \r )")))
      (is (= (str paren-prefix "(" span-suffix
                  keyword-prefix ":foo-bar" span-suffix "\n "
                  symbol-prefix "some-map" span-suffix
                  paren-prefix ")" span-suffix)
             (to-html "(:foo-bar\n some-map)"))))))

(deftest strings-tokenization
  (do
    (is (=
         (str string-prefix "\"coisa\"" span-suffix)
         (to-html "\"coisa\"")))
    (is (=
         (str paren-prefix   "("        span-suffix
              symbol-prefix  "assoc"    span-suffix " "
              symbol-prefix  "some-map" span-suffix " "
              keyword-prefix ":foo"     span-suffix " "
              string-prefix  "\"bar\""  span-suffix
              paren-prefix   ")"        span-suffix)
         (to-html "(assoc some-map :foo \"bar\")")))))

(deftest namespace-tokenization
  (is (= (str namespace-prefix "coisa" span-suffix
              symbol-prefix    "/thing"  span-suffix)
         (to-html "coisa/thing"))))
