(defproject climbstats "0.0.1-SNAPSHOT"
  :description "Clojure REST API"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.9.0"] ; clojure version
                 [org.clojure/tools.logging "0.4.0"]
                 [compojure "1.6.1"] ; routing library
                 [metosin/compojure-api "1.1.12"] ; improves compojure experience
                 [org.clojure/java.jdbc "0.7.5"] ; jdbc support
                 [ring/ring-json "0.4.0"] ; ring server json support
                 [ring/ring-defaults "0.3.1"] ; ring server default dependencies
                 [jarohen/nomad "0.7.3"] ; configuration handling library
                 [prismatic/schema "1.1.9"] ; bean validation equivalent for clojure
                 [korma "0.4.3"] ; orm
                 [com.h2database/h2 "1.4.197"] ; h2 database support
                 [ragtime "0.7.2"] ; database migration utility
                 ]

  :plugins [[lein-ring "0.12.4"]
            [venantius/ultra "0.5.2"] ; colorized test output
            ]

  :profiles { :test {:resource-paths ["resources/test"]
                     :dependencies [[cheshire "5.8.0"] ; json parsing library
                                   ]}
              :dev {:resource-paths ["resources/dev"]
                    :dependencies [[javax.servlet/servlet-api "2.5"]
                                   [ring/ring-mock "0.3.2"]
                                   ]
                     :ring {:port 8080}}
              :uberjar {:resource-paths ["resources/prod"]
                        :ring {:port 8080}}}

  :ring { :handler climbstats.handler/app :browser-uri "/swagger"}

  :aliases {"migrate"  ["run" "-m" "climbstats.config.database/ragtime-migrate"]
            "rollback" ["run" "-m" "climbstats.config.database/ragtime-rollback"]})
