(ns bobkonf.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [bobkonf.resources :as r]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (ANY "/" [] (r/users))
           (ANY "/:id" [id] (r/user id))
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
