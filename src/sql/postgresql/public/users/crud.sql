/*:name crud.insert */
/*:cardinality :one */
/*:malli/in sql.postgresql.public.users.schema/input */
/*:malli/out sql.postgresql.public.users.schema/output */
INSERT INTO users (
  id,
  email,
  status,
  created_at
)
VALUES (
  /*$id default-to */DEFAULT,
  /*$email*/'user@example.com',
  /*$status*/'sample',
  /*$created-at*/CURRENT_TIMESTAMP
)
RETURNING *

/*:name crud.insert-many */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:rows [:sequential sql.postgresql.public.users.schema/input]]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
INSERT INTO users (
  id,
  email,
  status,
  created_at
)
VALUES
/*%for row in rows separating , */
(
  /*$row.id default-to */DEFAULT,
  /*$row.email*/'user@example.com',
  /*$row.status*/'sample',
  /*$row.created-at*/CURRENT_TIMESTAMP
)
/*%end */
RETURNING *

/*:name crud.upsert-by-email */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:inserts sql.postgresql.public.users.schema/input] [:updates [:maybe sql.postgresql.public.users.schema/input-all-optional]]] */
/*:malli/out sql.postgresql.public.users.schema/output */
INSERT INTO users (
  id,
  email,
  status,
  created_at
)
VALUES (
  /*$inserts.id default-to */DEFAULT,
  /*$inserts.email*/'user@example.com',
  /*$inserts.status*/'sample',
  /*$inserts.created-at*/CURRENT_TIMESTAMP
)
ON CONFLICT ON CONSTRAINT users_email_key
/*%if updates */
DO UPDATE
SET
  /*%for item in updates separating , */
    /*!item.name*/created_at = /*$item.value*/CURRENT_TIMESTAMP
  /*%end */
/*%else => DO NOTHING */
/*%end */
RETURNING *

/*:name crud.upsert-by-id */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:inserts sql.postgresql.public.users.schema/input-all-required] [:updates [:maybe sql.postgresql.public.users.schema/input-all-optional]]] */
/*:malli/out sql.postgresql.public.users.schema/output */
INSERT INTO users (
  id,
  email,
  status,
  created_at
)
VALUES (
  /*$inserts.id*/1,
  /*$inserts.email*/'user@example.com',
  /*$inserts.status*/'sample',
  /*$inserts.created-at*/CURRENT_TIMESTAMP
)
ON CONFLICT ON CONSTRAINT users_pkey
/*%if updates */
DO UPDATE
SET
  /*%for item in updates separating , */
    /*!item.name*/created_at = /*$item.value*/CURRENT_TIMESTAMP
  /*%end */
/*%else => DO NOTHING */
/*%end */
RETURNING *

/*:name crud.get-by-email */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:email string?]] */
/*:malli/out [:maybe sql.postgresql.public.users.schema/output] */
SELECT * FROM users
WHERE email = /*$email*/'user@example.com'

/*:name crud.get-by-id */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:id int?]] */
/*:malli/out [:maybe sql.postgresql.public.users.schema/output] */
SELECT * FROM users
WHERE id = /*$id*/1

/*:name crud.count */
/*:cardinality :one */
/*:malli/in [:map {:closed true}] */
/*:malli/out [:map {:closed true} [:count int?]] */
SELECT COUNT(*) AS count FROM users

/*:name crud.count-by-email-starting-with */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:email string?]] */
/*:malli/out [:map {:closed true} [:count int?]] */
SELECT COUNT(*) AS count FROM users
WHERE email LIKE /*$email*/'user@example.com%' ESCAPE '\'

/*:name crud.count-by-status */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:status string?]] */
/*:malli/out [:map {:closed true} [:count int?]] */
SELECT COUNT(*) AS count FROM users
WHERE status = /*$status*/'sample'

/*:name crud.count-by-status-and-created-at */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:status string?] [:created-at [:fn bisql.schema/offset-date-time?]]] */
/*:malli/out [:map {:closed true} [:count int?]] */
SELECT COUNT(*) AS count FROM users
WHERE status = /*$status*/'sample'
  AND created_at = /*$created-at*/CURRENT_TIMESTAMP

/*:name crud.list-by-email-starting-with */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:email string?] [:limit bisql.schema/malli-limit] [:offset bisql.schema/malli-offset]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
SELECT * FROM users
WHERE email LIKE /*$email*/'user@example.com%' ESCAPE '\'
ORDER BY email
LIMIT /*$limit*/100
OFFSET /*$offset*/0

/*:name crud.list-by-status */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:status string?] [:limit bisql.schema/malli-limit] [:offset bisql.schema/malli-offset]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
SELECT * FROM users
WHERE status = /*$status*/'sample'
ORDER BY created_at
LIMIT /*$limit*/100
OFFSET /*$offset*/0

/*:name crud.list-by-status-and-created-at */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:status string?] [:created-at [:fn bisql.schema/offset-date-time?]] [:limit bisql.schema/malli-limit] [:offset bisql.schema/malli-offset]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
SELECT * FROM users
WHERE status = /*$status*/'sample'
  AND created_at = /*$created-at*/CURRENT_TIMESTAMP
LIMIT /*$limit*/100
OFFSET /*$offset*/0

/*:name crud.list-order-by-email */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:limit bisql.schema/malli-limit] [:offset bisql.schema/malli-offset]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
SELECT * FROM users
ORDER BY email
LIMIT /*$limit*/100
OFFSET /*$offset*/0

/*:name crud.list-order-by-id */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:limit bisql.schema/malli-limit] [:offset bisql.schema/malli-offset]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
SELECT * FROM users
ORDER BY id
LIMIT /*$limit*/100
OFFSET /*$offset*/0

/*:name crud.list-order-by-status-and-created-at */
/*:cardinality :many */
/*:malli/in [:map {:closed true} [:limit bisql.schema/malli-limit] [:offset bisql.schema/malli-offset]] */
/*:malli/out [:sequential sql.postgresql.public.users.schema/output] */
SELECT * FROM users
ORDER BY status, created_at
LIMIT /*$limit*/100
OFFSET /*$offset*/0

/*:name crud.update-by-email */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:where [:map {:closed true} [:email string?]]] [:updates sql.postgresql.public.users.schema/input-all-optional]] */
/*:malli/out sql.postgresql.public.users.schema/output */
UPDATE users
SET
/*%for item in updates separating , */
  /*!item.name*/created_at = /*$item.value*/CURRENT_TIMESTAMP
/*%end */
WHERE email = /*$where.email*/'user@example.com'
RETURNING *

/*:name crud.update-by-id */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:where [:map {:closed true} [:id int?]]] [:updates sql.postgresql.public.users.schema/input-all-optional]] */
/*:malli/out sql.postgresql.public.users.schema/output */
UPDATE users
SET
/*%for item in updates separating , */
  /*!item.name*/created_at = /*$item.value*/CURRENT_TIMESTAMP
/*%end */
WHERE id = /*$where.id*/1
RETURNING *

/*:name crud.delete-by-email */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:email string?]] */
/*:malli/out sql.postgresql.public.users.schema/output */
DELETE FROM users
WHERE email = /*$email*/'user@example.com'
RETURNING *

/*:name crud.delete-by-id */
/*:cardinality :one */
/*:malli/in [:map {:closed true} [:id int?]] */
/*:malli/out sql.postgresql.public.users.schema/output */
DELETE FROM users
WHERE id = /*$id*/1
RETURNING *