SELECT id, email, created_at -- narrowed the selected columns
FROM users
WHERE status = /*$status*/'active'
ORDER BY id
LIMIT /*$limit*/100
