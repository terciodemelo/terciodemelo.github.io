(ns main
  (:gen-class)
  (:use [hiccup.core]))

(defn -main [& args]
  (println (html [:head
                  [:link {:rel "stylesheet" :href "index.css"}]]
                 [:body
                  [:p "New website is comming along"]]
                 [:footer
                  [:span "2020-2021"]])))
