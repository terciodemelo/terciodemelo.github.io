(ns pages.resume
  (:require [pages.resume-pdf :as pdf]))

(defn content []
  [:div.pruned-resume
   [:a.download
    {:href "/resume_pdf.pdf" :download "tercio-de-melo.pdf"}
    "Download as PDF"]
   (pdf/content)])
