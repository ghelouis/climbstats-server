(ns climbstats.integration.config.routes-test
  (:import [java.util.Date])
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]
            [climbstats.handler :refer :all]
            [climbstats.config.database :as database]))

(def toto-climbing-session {:username "Toto" :location "Rainbowland" :comments "top!" :date (str "2012-09-17T16:47:52Z") :routes [{:color "jaune" :nb_climbed 8} {:color "vert" :nb_climbed 3}]})
(def climbing-session-to-create
  {:username "Toto" :location "Rainbowland" :comments "a comment" :date (str "2018-04-23T13:25:13.186Z") :routes [{:color "vert" :nb_climbed 5}]})
(def climbing-session-to-create-with-new-user {:username "Tata" :location "Rainbowland" :comments "a comment" :date (str "2018-04-23T13:25:13.186Z") :routes [{:color "jaune" :nb_climbed 4}]})
(def expected-climbing-sessions
  (json/generate-string [toto-climbing-session
                         {:username "Tutu" :location "Rainbowland" :comments "génial!" :date (str "2017-02-13T09:45:43Z") :routes []}]))
(def expected-user-sessions
  (json/generate-string [toto-climbing-session]))
(def expected-users
  (json/generate-string [{:name "Toto"} {:name "Tutu"}]))
(def expected-users-after-new-insert
  (json/generate-string [{:name "Toto"} {:name "Tutu"} {:name "Tata"}]))
(def expected-locations
  (json/generate-string [{:name "Rainbowland" :colors ["jaune", "vert", "bleu", "rouge", "noir", "violet"]}
                         {:name "Climbland" :colors ["jaune", "vert", "bleu"]}]))
(def expected-points
  (json/generate-string [{:name "Rainbowland" :colors [{:name "jaune" :value 1}, {:name "vert" :value 3}, {:name "bleu" :value 7},
                                                       {:name "rouge" :value 21}, {:name "noir" :value 50}, {:name "violet" :value 100}]}
                         {:name "Climbland" :colors [{:name "jaune" :value 3}, {:name "vert" :value 10}, {:name "bleu" :value 30}]}]))

(defn get-content-type [response]
  (:Content-Type (clojure.walk/keywordize-keys (:headers response))))

(database/ragtime-migrate)

(deftest test-climbing-session-routes
  (testing "GET /climbing-sessions"
    (let [response (app (mock/request :get "/climbing-sessions"))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) expected-climbing-sessions))))
  (testing "GET /climbing-sessions/user/Toto"
    (let [response (app (mock/request :get "/climbing-sessions/user/Toto"))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) expected-user-sessions))))
  (testing "POST /climbing-sessions"
    (let [response (app (mock/json-body (mock/request :post "/climbing-sessions") climbing-session-to-create))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 201))))
  (testing "POST /climbing-sessions with non-existing user"
    (let [response (app (mock/json-body (mock/request :post "/climbing-sessions") climbing-session-to-create-with-new-user))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 201)))
    (let [response (app (mock/request :get "/users"))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) expected-users-after-new-insert)))))

(deftest test-user-routes
  (testing "GET /users"
    (let [response (app (mock/request :get "/users"))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) expected-users)))))

(deftest test-location-routes
  (testing "GET /locations/colors"
    (let [response (app (mock/request :get "/locations/colors"))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) expected-locations))))
  (testing "GET /locations/colors-and-points"
    (let [response (app (mock/request :get "/locations/colors-and-points"))]
      (is (= (get-content-type response) "application/json; charset=utf-8"))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) expected-points)))))
