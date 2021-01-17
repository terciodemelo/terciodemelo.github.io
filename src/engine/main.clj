(ns engine.main
  (:use [hiccup.core]))

(defn -main [& args]
  (println (html [:head
                  [:link {:rel "stylesheet"
                          :href "index.css"}]]
                 [:body
                  [:p "New site on the way"]]
                 [:footer
                  [:p "2021"]])))
