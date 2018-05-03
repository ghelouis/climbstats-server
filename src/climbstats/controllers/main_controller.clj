(ns climbstats.controllers.main-controller
  (:use [ring.util.response :only [response]])
  (:require [compojure.core :refer :all]
            [ring.util.http-response :refer :all]
            [clojure.tools.logging :as log]
            [climbstats.services.climbing-session-service :as service]
            [climbstats.config.database :as database]))

(defn get-all-climbing-sessions []
  (log/info "get-all-climbing-sessions")
  (response
    (service/get-all-climbing-sessions)))

(defn create-new-climbing-session [climbing-session]
  (log/info (str "create-new-climbing-session : " climbing-session))
  (let [id-climbing-session-created (service/create-new-climbing-session climbing-session)]
    (if (nil? id-climbing-session-created) {:status 400}
      (created (str "/climbing-sessions/" id-climbing-session-created) (service/get-climbing-session id-climbing-session-created)))))

(defn get-climbing-sessions [username]
  (log/info (str "get-climbing-sessions for: " username))
  (response
    (service/get-climbing-sessions username)))

(defn get-all-users []
  (log/info "get-all-users")
  (response
    (service/get-all-users)))

(defn get-all-locations-colors []
  (log/info "get-all-locations-colors")
  (response
    (service/get-all-locations-colors)))

(defn get-all-locations-colors-and-points []
  (log/info "get-all-locations-colors-and-points")
  (response
    (service/get-all-locations-colors-and-points)))
