(ns climbstats.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [climbstats.config.routes :as config-routes]))

(def app config-routes/app-routes)
