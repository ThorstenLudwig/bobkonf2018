(ns bobkonf.core
  (:require [digest :as digest]
            [bobkonf.database :as db]))

(defn hash-email [email]
  (digest/sha-1 email))

(defn user-exists? [id]
  (let [response (db/get-user {:id id})]
    (not-empty response)))

(defn get-user [id]
  (-> (db/get-user {:id id})
      first))

(defn user-update! [id new-values]
  (-> (get-user id)
      (merge new-values)
      db/update-user!))

(defn create-user [user]
  (let [id       (hash-email (:email user))
        new-user (assoc user :id id)]
  (db/create-user! new-user)
  new-user))

(defn get-all-user []
  (db/all-user))

(defn delete-user [id]
  (db/delete-user! {:id id}))