(ns main
  (:gen-class)
  (:use [hiccup.core])
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn head [should-refresh]
  [:head
   [:link {:rel "stylesheet" :href (str "index.css?" (rand 1000))}]
   (if should-refresh
     [:meta {:http-equiv "refresh"  :content "2"}])])

(defn body [page]
  (let [module  (symbol (str "pages." page))
        content (symbol (str module "/content"))]
    (require module)
    [:body ((resolve content))]))

(defn footer []
  [:footer
   [:span "2018-2021"]])

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
        (binding [*out* wtr]
          (println (html (head is-dev-env)
                         [:body (body page)]
                         (footer))))))))

(let [class-path (System/getProperty "java.class.path")]
  (if (.contains class-path "/cider-nrepl/")
    (do
      (println "Watching page changes")
      (require '[juxt.dirwatch :refer [watch-dir]])
      ((resolve 'watch-dir)
       (fn [& _] (-main "--environment=dev"))
       (io/file "src/pages")))))
