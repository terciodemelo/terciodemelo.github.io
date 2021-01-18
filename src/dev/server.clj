(ns dev.server
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defn redirect-to-index [request]
  {:status 302 :headers {"Location" "/index.html"}})

(def routes
  (route/expand-routes
   #{["/" :get redirect-to-index :route-name :index-slash]}))

(defn create-server [port]
  (http/create-server
   {::http/type          :jetty
    ::http/resource-path ""
    ::http/routes        routes
    ::http/port          port}))

(defn start [port]
  (println (str "Serving pages at port " port))
  (http/start (create-server (Integer/parseInt (str port)))))
