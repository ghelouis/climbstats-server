(ns climbstats.config.swagger)

(def swagger-routes
  {:swagger
   {:ui "/swagger"
    :spec "/swagger.json"
    :data {:info {:title "Climbstats API"
                  :description "Manage user stats"}
           :tags [{:name "climbing-sessions"} {:name "users"} {:name "locations"}]}}})
