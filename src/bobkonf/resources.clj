(ns bobkonf.resources
  (:require [liberator.core :refer [defresource]]
            [bobkonf.core :as core]))

(defn body [ctx]
  (get-in ctx [:request :body]))

(defresource user [id]
  :allowed-methods [:get :patch :delete]
  :exists? (fn [ctx] (core/user-exists? id))
  :handle-ok (fn [ctx] (core/get-user id))
  :handle-not-found (fn [ctx] {:message "User not found"})
  :delete! (fn [ctx] (core/delete-user! id))
  :patch! (fn [ctx] (core/update-user! id)))



(defresource users []
  :allowed-methods [:get :put]
  :put! (fn [ctx] {:user (core/create-user! (body ctx))})
  :handle-created (fn [ctx] (:user ctx))
  :conflict? (fn [ctx] (-> (body ctx)
                           (:email)
                           core/hash-email
                           core/user-exists?))
  :handle-ok (fn [ctx] (core/get-all-user)))
