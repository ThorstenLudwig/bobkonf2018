(ns bobkonf.core
  (:require [digest :as digest]
            [bobkonf.database :as db]))

(defn hash-email [email]
  (digest/sha-1 email))

(defn user-exists? [id]
  (not-empty (db/get-user {:id id})))

(defn get-user [id]
  (first (db/get-user {:id id})))

(defn delete-user! [id]
  )

(defn update-user! [id]
  )

(defn create-user! [user]
  )

(defn get-all-user []
  )
