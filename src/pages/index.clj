(ns pages.index
  (:gen-class)
  (:require [parses.clojure :as parse]))

(defn content []
  (list [:h2 "New website!"]
        
        [:p "New website is coming along!!"]
        [:p "We now even have multiple paragraphs..."]
        
        [:h2 "No JavaScript"]
        
        [:p "This website is made without any javascript."]
        [:p
         "Though the website itself is HTML and CSS only, it is generated from "
         "clojure code. I have previously used "
         [:a {:href "https://gohugo.io/"} "Hugo"]
         " to generate static website, "
         "but it is too cumbersome. I wanted something simple, but with powerful "
         "features such as hot reloading and custom logic for building the pages."]

        [:h2 "Clojure for the rescue"]

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
        
        [:div
         [:a.pre-title
          {:href "https://github.com/terciodemelo/terciodemelo.github.io/blob/master/src/main.clj"
           :target "_blank"} "src/main.clj"]
         [:pre (parse/to-html (slurp "src/main.clj"))]]
        
        [:p
         "The " [:code "&lt;meta ...&gt;"] " tag triggers refresh every too second. "
         "And every time I save a file, say "
         [:code "src/pages/index.clj"]
         ", in "
         [:a {:href "https://www.gnu.org/software/emacs/"} "Emacs"]
         " then "
         [:a {:href "https://github.com/clojure-emacs/cider"} "CIDER"]
         " automatically creates a new "
         [:code "index.html"]
         ". Notice that I'm using "
         [:a {:href "https://github.com/juxt/dirwatch"} "juxt.dirwatch"]
         " to watch page changes locally. This watch is executed only when CIDER"
         " is in the class path, which doesn't happen when we run it with a "
         [:code "lein run"]
         ", for example."]

        [:p
         "Who already had some contact with "
         [:a {:href "https://clojurescript.org/"} "ClojureScript"]
         " might have noticed that I leverage "
         [:a {:href "https://github.com/weavejester/hiccup"} "Hiccup"]
         " to generate HTML from Clojure data structures. It is a really fun and"
         " powerful tool."]

        [:p "If you are interested in seing the details, you will find the code in "
         [:a {:href "https://github.com/terciodemelo/terciodemelo.github.io"} "GitHub"]
         ". This website is hosted in "
         [:a {:href "https://pages.github.com/"} "GitHub Pages"]
         "."]
        ))
