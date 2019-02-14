DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE TABLE users
(
  user_id           INTEGER PRIMARY KEY,
  first_name        VARCHAR NOT NULL,
  second_name       VARCHAR NOT NULL,
  email             VARCHAR,
  password          VARCHAR,
  token             VARCHAR NOT NULL,
  registered        TIMESTAMP DEFAULT now()
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

