(ns components.components
  (:gen-class))

(def pages [{:href "/index.html"  :name "index"  :label "HOME"}
            {:href "/about.html"  :name "about"  :label "ABOUT"}
            {:href "/posts.html"  :name "posts"  :label "POSTS"}
            {:href "/tags.html"   :name "tags"   :label "TAGS"}
            {:href "/resume.html" :name "resume" :label "RESUMÉ"}])

(defn nav-link-attributes [page current-page-name]
  (if (= (:name page) current-page-name)
    {:href (:href page) :class "current-page"}
    {:href (:href page)}))

(defn header [current-page]
  (if (not= current-page "resume_pdf")
    [:div.header
     [:h1 "Tercio de Melo"]
     [:nav (for [page pages]
             [:a (nav-link-attributes page current-page) (:label page)])]]))
