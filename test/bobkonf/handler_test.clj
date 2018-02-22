(ns bobkonf.handler-test
  (:require [ring.mock.request :as mock]
            [midje.sweet :refer :all]
            [cheshire.core :as json]
            [bobkonf.handler :refer :all]
            [bobkonf.database :as db]
            [clojure.walk :refer [keywordize-keys]]))

(db/remove-all-user!)


(facts "About an empty database"
       (fact "It returns an empty response"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200
               (json/parse-string (:body response)) => []))
       (fact "It failes to delete an user that does not exist"
             (let [response (app (mock/request :delete "/123"))]
               (:status response) => 404))
       (fact "It failes to return a user that does not exist"
             (let [response (app (mock/request :get "/123"))]
               (:status response) => 404))
       (fact "It failes to modify a user that does not exist"
             (let [response (app (mock/request :post "/123"))]
               (:status response) => 404)))

(facts "About inserting user"
       (fact "It adds a user"
             (let [response (-> (mock/request :put "/")
                                (mock/header "Content-Type" "application/json")
                                (mock/body {:email    "Thorsten@Ludwig.de"
                                            :username "DerThorsten"
                                            :password "1SehrSicheresPassword!"})
                                app)
                   user (-> (:body response)
                            json/parse-string
                            keywordize-keys)]
               (:status response) => 201
               user => (contains {:email    "Thorsten@Ludwig.de"
                                  :username "DerThorsten"})))
       (fact "It finds a created user"
             (let [response (app (mock/request :get "/1d8aeec6c4d0b3318f9ce1b064d84ac8d35e5ce9"))]
               (:status response) => 200))
       (fact "It finds users"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200
               (count (json/parse-string (:body response))) => 1))
       (fact "It modifies an user"
             (let [response (-> (mock/request :post "/1d8aeec6c4d0b3318f9ce1b064d84ac8d35e5ce9")
                                (mock/body {:email    "Yahya.Thorsten@Ludwig.de"})
                                app)]
               (:status response) => 201))
       (fact "It deletes an user"
             (let [response (app (mock/request :delete "/1d8aeec6c4d0b3318f9ce1b064d84ac8d35e5ce9"))]
               (:status response) => 204)))

(def test-user {:username "Foo" :email "Bar" :id "123" :password "123456"})

(facts "Mocking requests"
       (fact "It finds an user when mocking"
             (against-background
               (db/get-user {:id "123"}) => [test-user])
             (let [response (app (mock/request :get "/123"))]
               (:status response) => 200
               (keywordize-keys (json/parse-string (:body response))) => test-user)))
