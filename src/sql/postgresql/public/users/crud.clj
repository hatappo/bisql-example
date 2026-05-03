(ns sql.postgresql.public.users.crud)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 108
           :sql/query-name "crud.count"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:108"}
 count)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 114
           :sql/query-name "crud.count-by-email-starting-with"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:114"}
 count-by-email-starting-with)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 121
           :sql/query-name "crud.count-by-status"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:121"}
 count-by-status)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 128
           :sql/query-name "crud.count-by-status-and-created-at"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:128"}
 count-by-status-and-created-at)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 217
           :sql/query-name "crud.delete-by-email"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:217"}
 delete-by-email)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 225
           :sql/query-name "crud.delete-by-id"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:225"}
 delete-by-id)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 94
           :sql/query-name "crud.get-by-email"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:94"}
 get-by-email)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 101
           :sql/query-name "crud.get-by-id"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:101"}
 get-by-id)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 1
           :sql/query-name "crud.insert"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:1"}
 insert)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 19
           :sql/query-name "crud.insert-many"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:19"}
 insert-many)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 136
           :sql/query-name "crud.list-by-email-starting-with"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:136"}
 list-by-email-starting-with)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 146
           :sql/query-name "crud.list-by-status"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:146"}
 list-by-status)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 156
           :sql/query-name "crud.list-by-status-and-created-at"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:156"}
 list-by-status-and-created-at)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 166
           :sql/query-name "crud.list-order-by-email"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:166"}
 list-order-by-email)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 175
           :sql/query-name "crud.list-order-by-id"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:175"}
 list-order-by-id)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 184
           :sql/query-name "crud.list-order-by-status-and-created-at"
           :cardinality :many
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:184"}
 list-order-by-status-and-created-at)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 193
           :sql/query-name "crud.update-by-email"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:193"}
 update-by-email)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 205
           :sql/query-name "crud.update-by-id"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:205"}
 update-by-id)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 40
           :sql/query-name "crud.upsert-by-email"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:40"}
 upsert-by-email)

(declare ^{:arglists (quote ([datasource] [datasource template-params]))
           :sql/file "src/sql/postgresql/public/users/crud.sql"
           :sql/line 67
           :sql/query-name "crud.upsert-by-id"
           :cardinality :one
           :bisql.define/navigation-stub true
           :doc "This function is generated from SQL: src/sql/postgresql/public/users/crud.sql:67"}
 upsert-by-id)
