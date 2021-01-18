(defproject . "0.1.0-SNAPSHOT"
  :description "Personal website"
  :url "https://tercio.com.br"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [hiccup "2.0.0-alpha2"]
                 [io.pedestal/pedestal.service "0.5.8"]
                 [io.pedestal/pedestal.jetty "0.5.8"]
                 [org.slf4j/slf4j-simple "1.7.28"]
                 [juxt/dirwatch "0.2.5"]]
  :main main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :aliases {"dev-server" ["run" "-m" "dev.server/start"]}
  :resource-paths ["tercio.com.br"])
