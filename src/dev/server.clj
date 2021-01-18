(ns dev.server
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defn redirect-to-index [request]
  {:status 302 :headers {"Location" "/index.html"}})

(def routes
  (route/expand-routes
   #{["/" :get redirect-to-index :route-name :index-slash]}))

(defn create-server []
  (http/create-server
   {::http/type          :jetty
    ::http/resource-path ""
    ::http/routes        routes
    ::http/port          8000}))

(defn start []
  (println "Serving at port 8000")
  (http/start (create-server)))
