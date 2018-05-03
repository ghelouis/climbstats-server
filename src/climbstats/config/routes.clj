(ns climbstats.config.routes
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [climbstats.config.swagger :as swagger-conf]
            [climbstats.controllers.main-controller :as controller]
            [climbstats.model.climbing-session :as model]))

(def climbing-session-routes
  (context "/climbing-sessions" [] :tags ["climbing-sessions"]
    (GET "/" []
      :summary "Get all climbing sessions"
      :return [model/climbing-session-schema]
      :responses {200 {:schema [model/climbing-session-schema],
                       :description "List of climbing sessions"}}
      (controller/get-all-climbing-sessions))
    (POST "/" []
      :summary "Create a new climbing session and the corresponding user if he doesn't exist"
      :body [climbing-session model/climbing-session-schema]
      :return model/climbing-session-schema
      :responses {201 {:schema model/climbing-session-schema,
                       :description "Return the created climbing session"}
                  400 {:description "Malformed request body"}}
      (controller/create-new-climbing-session climbing-session))
    (GET "/user/:username" []
      :path-params [username :- String]
      :return [model/climbing-session-schema]
      :responses {200 {:schema [model/climbing-session-schema],
                       :description "The climbing sessions found for this user"}
                  404 {:description "No climbing session found for this username"}}
      :summary "Get a specific climbing session by username"
      (controller/get-climbing-sessions username))))

(def user-routes
  (context "/users" [] :tags ["users"]
    (GET "/" []
      :summary "Get all users"
      :return [model/user-schema]
      :responses {200 {:schema [model/user-schema],
                       :description "List of users"}}
      (controller/get-all-users))))

(def location-routes
  (context "/locations" [] :tags ["locations"]
    (GET "/colors" []
      :summary "Get all the locations with their associated colors"
      :return [model/location-with-colors-schema]
      :responses {200 {:schema [model/location-with-colors-schema],
                       :description "List of locations and their associated colors"}}
      (controller/get-all-locations-colors))
    (GET "/colors-and-points" []
      :summary "Get all the locations with their associated colors and points"
      :return [model/location-with-colors-and-points-schema]
      :responses {200 {:schema [model/location-with-colors-and-points-schema],
                       :description "List of locations and their associated colors and points"}}
      (controller/get-all-locations-colors-and-points))))

(defapi app-routes
   swagger-conf/swagger-routes
   climbing-session-routes
   user-routes
   location-routes
  (undocumented (compojure.route/not-found (ok {:not "found"}))))
