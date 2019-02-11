DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY,
  first_name        VARCHAR NOT NULL,
  second_name        VARCHAR NOT NULL,
  email             VARCHAR NOT NULL,
  password          VARCHAR NOT NULL,
  registered        TIMESTAMP DEFAULT now(),
);

