(ns bobkonf.resources
  (:require [liberator.core :refer [defresource]]
            [bobkonf.core :as core]))

(defn body [ctx]
  (get-in ctx [:request :body]))

(defresource user [id]
  :allowed-methods       [:get :post :delete]
  :available-media-types ["application/json"]
  :exists?               (fn [ctx] (let [method (get-in ctx [:request :request-method])]
                                     (core/user-exists? id)))
  :handle-not-found      (fn [ctx] {:message "User not found"})
  :handle-ok             (fn [ctx] (core/get-user id))
  :delete!               (fn [ctx] (core/delete-user id))
  :post!                 (fn [ctx] (core/user-update! id (body ctx))))


(defresource users []
  :allowed-methods       [:get :put]
  :available-media-types ["application/json"]
  :conflict?             (fn [ctx]
                           (-> (body ctx)
                               :email
                               core/hash-email
                               core/user-exists?))
  :put!                  (fn [ctx] {:user (core/create-user (body ctx))})
  :handle-created        (fn [ctx] (:user ctx))
  :handle-ok             (fn [ctx] (core/get-all-user)))