(ns climbstats.config.database
  (:use [korma.db :only [defdb h2]])
  (:require [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [ragtime.jdbc :as ragtime-jdbc]
            [ragtime.repl :as ragtime-repl]
            [climbstats.config.app :as appconf]))

(defdb db-h2-korma (h2 (get (appconf/conf) :db-spec)))
(def db-h2-connection (get (appconf/conf) :db-spec))

(defn load-ragtime-config []
  {:datastore  (ragtime-jdbc/sql-database (get (appconf/conf) :db-spec))
   :migrations (ragtime-jdbc/load-directory (io/resource "migrations"))})

(defn ragtime-migrate
  "Apply database migration scripts"
  []
  (log/info "Applying ragtime migrations")
  (ragtime-repl/migrate (load-ragtime-config)))

(defn ragtime-rollback
  "Apply database rollback scripts"
  []
  (log/info "Rollbacking ragtime migrations")
  (ragtime-repl/rollback (load-ragtime-config)))
