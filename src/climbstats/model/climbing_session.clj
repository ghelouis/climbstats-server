(ns climbstats.model.climbing-session
  (:use [korma.core :only [defentity table entity-fields pk has-many belongs-to]])
  (:require [schema.core :as schema]))

(declare climbing-session user location color)

; -- Entities
(defentity user
  (has-many climbing-session))

(defentity location
  (has-many climbing-session)
  (has-many color))

(defentity color
  (belongs-to location))

(defentity climbing-session
  (table :climbing_session)
  (belongs-to user)
  (belongs-to location))

(defentity route
  (belongs-to climbing-session)
  (belongs-to color))


; -- Schemas for REST incoming/outgoing data
(def climbing-session-schema
  {:username schema/Str
   :location schema/Str
   :comments schema/Str
   :date schema/Inst
   :routes [{:color schema/Str :nb_climbed schema/Int}]})

(def user-schema
  {:name schema/Str})

(def location-with-colors-schema
  {:name schema/Str
   :colors [schema/Str]})

(def location-with-colors-and-points-schema
  {:name schema/Str
   :colors [{:name schema/Str :value schema/Int}]})
