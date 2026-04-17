# bisql-example

Small end-to-end example project for Bisql `0.2.1`.

This sample shows a realistic flow:

1. generate CRUD SQL from a database schema
2. generate declaration namespaces so the generated query functions can be required normally
3. execute one generated query function
4. copy and adapt one generated query into a custom SQL template
5. execute the custom query function

## Requirements

- Java via `mise`
- Either:
  - PostgreSQL already running locally, or
  - Docker / Docker Compose

If you use `mise`, trust this repository config and install Java first:

```sh
mise trust
mise install
```

## Files

- `bisql.edn`
- `src/sql.clj`
- `src/app/core.clj`
- `src/sql/postgresql/public/users/find-active.sql`
- `dev/schema.sql`
- `compose.yml` (optional local PostgreSQL)

## 1. Prepare PostgreSQL

### Option A: Use an existing local PostgreSQL

Create or choose a database, then load the sample schema:

```sh
psql -d bisql_example -f dev/schema.sql
```

If your local PostgreSQL uses different credentials, set the usual environment variables:

```sh
export PGHOST=localhost
export PGPORT=5432
export PGDATABASE=bisql_example
export PGUSER=postgres
export PGPASSWORD=postgres
```

### Option B: Start PostgreSQL with Docker

If you do not already have PostgreSQL running locally, use the bundled container:

```sh
docker compose down -v
docker compose up -d
docker compose ps
```

If you previously started the container with an older PostgreSQL image or an older volume layout, `docker compose down -v` resets the Docker volume so PostgreSQL 18 can initialize cleanly.

Wait until the container is `Up` (or `healthy`), then load the schema:

```sh
psql postgresql://bisql:bisql@localhost:54329/bisql_example -f dev/schema.sql
```

Or, if `psql` is not installed locally:

```sh
docker exec -i bisql-example-postgres psql -U bisql -d bisql_example < dev/schema.sql
```

For the Docker setup, run the example with:

```sh
export PGHOST=localhost
export PGPORT=54329
export PGDATABASE=bisql_example
export PGUSER=bisql
export PGPASSWORD=bisql
```

## 2. Define query functions once

```clojure
(ns sql
  (:require [bisql.core :as bisql]))

(bisql/defquery)
```

That single `(bisql/defquery)` call makes both generated SQL and hand-written SQL available as ordinary Clojure functions.

## 3. Generate CRUD SQL from the schema

This repository already includes a minimal `bisql.edn` that tells Bisql to write generated SQL under `src/sql`.

Run:

```sh
clojure -M:bisql gen-crud
```

This generates a file like:

```text
src/sql/postgresql/public/users/crud.sql
```

Inside it, you will see entries such as:

- `crud.get-by-id`
- `crud.count`
- `crud.list-by-status`
- `crud.count-by-status`

For this walkthrough, the generated query we start from is `crud.list-by-status`:

```sql
/* @name crud.list-by-status */
SELECT *
FROM users
WHERE status = /*$status*/'active'
ORDER BY status, created_at
LIMIT /*$limit*/100
OFFSET /*$offset*/0
```

## 4. Generate function namespaces

Because this sample uses ordinary `require` forms in `src/app/core.clj`, generate function namespace files after generating CRUD SQL:

```sh
clojure -M:bisql gen-functions
```

If you prefer, you can replace this step and the previous `gen-crud` step with a single `clojure -M:bisql gen-crud-and-functions`.

This writes declaration files such as:

```text
src/sql/postgresql/public/users/crud.clj
src/sql/postgresql/public/users/core.clj
```

These files are generated helper code, so they are ignored by `.gitignore`.

## 5. Execute one generated query

This sample executes the generated `list-by-status` query:

```sh
clojure -M -m app.core generated
```

That resolves and calls:

```clojure
(sql.postgresql.public.users.crud/list-by-status
 datasource
 {:status "active"
  :limit 10
  :offset 0})
```

Typical output:

```clojure
Running generated CRUD query:
[{:id 1, :email "alice@example.com", :status "active", :created-at ...}
 {:id 3, :email "carol@example.com", :status "active", :created-at ...}]
```

## 6. Copy and adapt one generated query

A common workflow is to start from a generated template and then tailor it.

For example, open `src/sql/postgresql/public/users/crud.sql`, copy the `crud.list-by-status` query block into a new file, and save it as:

```text
src/sql/postgresql/public/users/find-active.sql
```

A common reason to do this is to narrow the selected columns for the final application query.

Then simplify that copied block into this custom template:

```sql
SELECT id, email, created_at -- narrowed the selected columns
FROM users
WHERE status = /*$status*/'active'
ORDER BY id
LIMIT /*$limit*/100
```

This repository already includes that finished custom template. After you add or change custom SQL files of your own, run `clojure -M:bisql gen-functions` again so the matching function namespace files stay in sync.

## 7. Execute the custom query

```sh
clojure -M -m app.core custom
```

That resolves and calls:

```clojure
(sql.postgresql.public.users.core/find-active
 datasource
 {:status "active"
  :limit 10})
```

Typical output:

```clojure
Running custom query copied from a generated template:
[{:id 1, :email "alice@example.com", :created-at ...}
 {:id 3, :email "carol@example.com", :created-at ...}]
```

## 8. Clean up

If you used a local PostgreSQL database that you created just for this example, you can drop it yourself when you are done.

If you used Docker:

```sh
docker compose down
```

If you also want to remove the container volume and reset the database completely:

```sh
docker compose down -v
```
