(defproject bobkonf "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [com.h2database/h2 "1.4.196"]
                 [compojure "1.5.1"]
                 [digest "1.4.6"]
                 [liberator "0.15.1"]
                 [ring/ring-core "1.6.2"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [yesql "0.5.3"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler bobkonf.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
