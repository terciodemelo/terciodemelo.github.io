(ns main
  (:gen-class)
  (:use [hiccup.core])
  (:require [clojure.java.io :as io]
            [clojure.core.async :refer [go]]
            [juxt.dirwatch :refer [watch-dir]]
            [clojure.string :as string]
            [dev.server]
            [components.components :as components]))

(defn head [should-refresh]
  [:head
   [:link {:rel "stylesheet" :href (str "index.css?" (rand 1000))}]
   [:title "Tercio de Melo"]
   (if should-refresh
     [:meta {:http-equiv "refresh"  :content "2"}])])

(defn body [page]
  (let [module  (symbol (str "pages." page))
        content (symbol (str module "/content"))]
    (require module)
    [:body
     (components/header page)
     ((resolve content))]))

(defn pages [base-dir]
  (let [directory (io/file (str base-dir "/src/pages"))]
    (->> (.list directory)
         (filter #(string/ends-with? % ".clj"))
         (map #(string/replace % #"\.clj$" ""))
         vec)))

(defn -main [& args]
  (doseq [page (pages (System/getProperty "user.dir"))]
    (let [output-file (str "tercio.com.br/" page ".html")
          sysargs     (set args)
          is-dev-env  (contains? sysargs "--environment=dev")]
      (println "Generating " output-file)
      (with-open [wtr (io/writer output-file)]
        (.write wtr (html [:html (head is-dev-env)
                           [:body (body page)]]))))))

(defn watch-in-cider []
  (let [class-path (System/getProperty "java.class.path")]
    (if (.contains class-path "/cider-nrepl/")
      (do
        (println "Starting dev server on port 8000")
        (go (dev.server/start 8000))

        (println "Watching page changes")
        (watch-dir
         (fn [& _] (-main "--environment=dev"))
         (io/file "src/pages")
         (io/file "src/components")
         (io/file "src/posts"))))))

(comment (watch-in-cider))
(comment (-main))
(comment (-main "--environment=dev"))
(comment (dev.server/start 8000))
