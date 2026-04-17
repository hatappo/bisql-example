CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY,
  email TEXT NOT NULL UNIQUE,
  status TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS users_status_created_at_idx
  ON users (status, created_at);

TRUNCATE TABLE users RESTART IDENTITY;

INSERT INTO users (email, status)
VALUES
  ('alice@example.com', 'active'),
  ('bob@example.com', 'inactive'),
  ('carol@example.com', 'active');
