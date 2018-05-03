(ns climbstats.dao.climbing-session-dao
  (:use [korma.core :only [select insert values delete where set-fields with fields group]]
        [korma.db :only [transaction]])
  (:require [clojure.tools.logging :as log]
            [climbstats.model.climbing-session :as model])
  (:import (java.util UUID)))

(defn uuid [] (str (UUID/randomUUID)))

(defn get-all-climbing-sessions
  "Get all climbing sessions from the database"
  []
  (log/info "get-all-climbing-sessions")
  (select model/climbing-session (fields :id :comments :date)
    (with model/user (fields [:name :username]))
    (with model/location (fields [:name :location]))))

(defn create-new-climbing-session-and-routes
  "Add a new climbing session and associated routes to the database"
  [climbing-session routes]
  (log/info (str "create-new-climbing-session-and-routes dao: " climbing-session routes))
  (let [colors (:color (first (select model/location (where {:id (:location_id climbing-session)}) (with model/color))))]
    (transaction
      (insert model/climbing-session (values climbing-session))
      (doall (for [route routes]
        (let [col-id (:id (first (filter (fn [col] (= (:color route) (:name col))) colors)))
              id (uuid)
              session-id (:id climbing-session)
              nb-climbed (:nb_climbed route)]
          (insert model/route (values {:id id :color_id col-id :climbing_session_id session-id :nb_climbed nb-climbed}))))))))

(defn create-new-user
  "Add a new user to the database"
  [user]
  (log/info (str "create-new-user dao: " user))
  (insert model/user
    (values user)))

(defn get-climbing-session
  "Get only one climbing session using its id"
  [id]
  (log/info (str "get-climbing-session dao, id: " id))
  (select model/climbing-session (fields :comments :date)
    (where {:id id})
    (with model/user (fields [:name :username]))
    (with model/location (fields [:name :location]))))

(defn get-routes
  "Get routes of a climbing session"
  [climbing-session-id]
  (log/info (str "get-routes dao, climbing-session-id: " climbing-session-id))
  (select model/route (fields [:nb_climbed])
    (where {:climbing_session_id climbing-session-id})
    (with model/color (fields [:name]))))

(defn get-climbing-sessions
  "Get the climbing sessions for a specific user"
  [username]
  (log/info (str "get-climbing-sessions dao, username: " username))
  (select model/climbing-session (fields :id :comments :date)
    (with model/user (fields [:name :username]) (where {:name username}))
    (with model/location (fields [:name :location]))))

(defn get-all-users
  "Get all users from the database"
  []
  (log/info "get-all-users")
  (select model/user (fields :name)))

(defn get-user-by-name
  "Get the first user having the given name from the database"
  [name]
  (log/info "get-user-by-name")
  (first (select model/user (where {:name name}))))

(defn get-location-by-name
  "Get the first location having the given name from the database"
  [name]
  (log/info "get-location-by-name")
  (first (select model/location (where {:name name}))))

(defn extract-names [m]
  (into {} (for [[k v] m] [k (map :name v)])))

(defn add-map-keys [m]
  (for [[k v] m] {:name k :colors v}))

(defn add-map-keys-and-remove-inner-location [m]
  (for [[k v] m] {:name k :colors (map #(dissoc % :location) v)}))

(defn get-all-locations-colors
  "Get all locations and their associated colors from the database"
  []
  (log/info "get-all-locations-colors")
  (add-map-keys (extract-names
    (group-by :location (select model/color (fields [:name])
      (with model/location (fields [:name :location])))))))

(defn get-all-locations-colors-and-points
  "Get all locations and their associated colors and points from the database"
  []
  (log/info "get-all-locations-colors-and-points")
  (add-map-keys-and-remove-inner-location
    (group-by :location (select model/color (fields :name :value)
      (with model/location (fields [:name :location]))))))
