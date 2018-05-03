(ns climbstats.services.climbing-session-service
  (:use [clojure.set])
  (:require [climbstats.dao.climbing-session-dao :as dao]
            [clojure.tools.logging :as log])
  (:import (java.util UUID)))

(defn uuid [] (str (UUID/randomUUID)))

(defn get-all-climbing-sessions
  "Get all climbing sessions from the database"
  []
  (map #(dissoc % :id) (map (fn [session]
    (let [routes (map #(rename-keys % {:name :color}) (dao/get-routes (:id session)))]
      (assoc session :routes routes)))
        (dao/get-all-climbing-sessions))))

(defn create-new-user
  "Add a new user to the database"
  [id name]
  (dao/create-new-user {:id id :name name}))

(defn get-or-create-user
  "Get id of the first user with the given name or create it if he doesn't exist"
  [name]
  (or (:id (dao/get-user-by-name name))
      (let [id (uuid)] (create-new-user id name) id)))

(defn get-location-by-name
  "Get id of the first location having the given name from the database"
  [name]
  (:id (dao/get-location-by-name name)))

(defn create-new-climbing-session
  "Add a new climbing session and associated routes to the database, return the climbing session id if ok, null if invalid"
  [climbing-session]
  (let [routes (:routes climbing-session)]
    (let [id (uuid) user-id (get-or-create-user (:username climbing-session)) location-id (get-location-by-name (:location climbing-session))]
      (let [session (dissoc (assoc climbing-session :id id :user_id user-id :location_id location-id) :username :location :routes)]
        (log/info routes)
        (log/info session)
        (dao/create-new-climbing-session-and-routes session routes)
        id))))

(defn get-climbing-session
  "Get climbing session by its id"
  [climbing-session-id]
  (let [session (first (dao/get-climbing-session climbing-session-id))]
    (let [routes (map #(rename-keys % {:name :color}) (dao/get-routes climbing-session-id))]
      (assoc session :routes routes))))

(defn get-climbing-sessions
  "Get the climbing sessions for a specific user"
  [username]
  (map #(dissoc % :id) (map (fn [session]
    (let [routes (map #(rename-keys % {:name :color}) (dao/get-routes (:id session)))]
      (assoc session :routes routes)))
        (dao/get-climbing-sessions username))))

(defn get-all-users
  "Get all users from the database"
  []
  (dao/get-all-users))

(defn get-all-locations-colors
  "Get all locations and their associated colors from the database"
  []
  (dao/get-all-locations-colors))

(defn get-all-locations-colors-and-points
  "Get all locations and their associated colors and points from the database"
  []
  (dao/get-all-locations-colors-and-points))
