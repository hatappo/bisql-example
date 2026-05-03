(ns app.core
  (:require [clojure.pprint :as pprint]
            [next.jdbc :as jdbc]
            [sql]
            [sql.postgresql.public.users.core :as users.core]
            [sql.postgresql.public.users.crud :as users.crud]))

(def datasource
  (jdbc/get-datasource
   {:dbtype "postgresql"
    :host (or (System/getenv "BISQL_HOST") "localhost")
    :port (parse-long (or (System/getenv "BISQL_PORT") "5432"))
    :dbname (or (System/getenv "BISQL_DBNAME") "bisql_example")
    :user (or (System/getenv "BISQL_USER") "postgres")
    :password (or (System/getenv "BISQL_PASSWORD") "postgres")}))

(defn -main
  [& [mode]]
  (case mode
    "generated"

    (do
      (println "Running generated CRUD query:")
      (pprint/pprint
       (users.crud/list-by-status datasource
                                  {:status "active"
                                   :limit 10
                                   :offset 0})))

    "custom"
    (do
      (println "Running custom query copied from a generated template:")
      (pprint/pprint
       (users.core/find-active datasource
                               {:status "active"
                                :limit 10})))

    (do
      (println "Usage:")
      (println "  clojure -M -m app.core generated")
      (println "  clojure -M -m app.core custom"))))
