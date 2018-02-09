(ns bobkonf.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [bobkonf.resources :as r]))

(defroutes app-routes
           (ANY "/" [] (r/users))
           (ANY "/:id" [id] (r/user id))
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)
      (wrap-json-body {:keywords? true})))