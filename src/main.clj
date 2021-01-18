(ns main
  (:gen-class)
  (:use [hiccup.core])
  (:require [clojure.java.io :as io]))

(defn body []
  '([:h1 "Tercio de Melo"]
    [:p "New website is coming along!!"]
    [:p "We now even have multiple paragraphs..."]
    [:p "This website is made without any javascript."]
    
    [:p
     "Though the website itself is HTML and CSS only, it is generated from "
     "clojure code. I have previously used "
     [:a {:href "https://gohugo.io/"} "Hugo"]
     " to generate static website, "
     "but it is too cumbersome. I wanted something simple, but with powerful "
     "features such as hot reloading and custom logic for building the pages."]
    
    [:p
     "Of course a "
     [:a {:href "https://pt.wikipedia.org/wiki/Lisp"} "Lisp"]
     " would be the best tool for the job, code and data is "
     "the same, for being a homoiconic language; and "
     [:a {:href "https://clojure.org/"} "Clojure"]
     " is currently my flavor of Lisp."]
    
    [:p
     "Below is a snippet of the clojure code that for now generates this "
     [:code "index.html"]
     " file:"]
    
    [:pre
     "(defn -main [& args]\n"
     "  (with-open [wtr (io/writer \"tercio.com.br/index.html\")]\n"
     "    (binding [*out* wtr]\n"
     "      (println (html [:head\n"
     "                      [:link {:rel \"stylesheet\" :href \"index.css\"}]\n"
     "                      [:meta {:http-equiv \"refresh\"  :content \"2\"}]]\n"
     "                     [:body (body)]\n"
     "                     [:footer\n"
     "                      [:span \"2018-2021\"]])))))\n"
     "(-main)"]
    
    [:p
     "The " [:code "&lt;meta ...&gt;"] " tag triggers refresh every too second. "
     "And every time I save this file, it is executed by "
     [:a {:href "https://github.com/clojure-emacs/cider"} "CIDER"]
     " and a new "
     [:code "index.html"]
     " is built."]

    [:p
     "Who already had some contact with "
     [:a {:href "https://clojurescript.org/"} "ClojureScript"]
     " might have noticed that I leverage "
     [:a {:href "https://github.com/weavejester/hiccup"} "Hiccup"]
     " to generate HTML from Clojure data structures. It is a really fun and"
     " powerful tool."]

    [:p "If you are intersted in seing the details, you will find the code in "
     [:a {:href "https://github.com/terciodemelo/terciodemelo.github.io"} "GitHub"]
     ". This website is hosted in "
     [:a {:href "https://pages.github.com/"} "GitHub Pages"]
     "."]
    ))

(defn -main [& args]
  (with-open [wtr (io/writer "tercio.com.br/index.html")]
    (binding [*out* wtr]
      (println (html [:head
                      [:link {:rel "stylesheet" :href (str "index.css?" (rand 1000))}]
                      (comment [:meta {:http-equiv "refresh"  :content "2"}])]
                     [:body (body)]
                     [:footer
                      [:span "2018-2021"]])))))

(comment (-main))
