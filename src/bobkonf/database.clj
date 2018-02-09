(ns bobkonf.database
  (:require [yesql.core :refer [defqueries]]))


(def db-spec {:dbtype "h2" :dbname "./my-webapp"})

(defqueries "queries/user.sql" {:connection db-spec})

(drop-table!)
(create-user-table!)