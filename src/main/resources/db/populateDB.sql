DELETE FROM user_roles;
DELETE FROM users;

INSERT INTO users (user_id, first_name, second_name, email, token, password) VALUES
  (19105970, 'Пётр', 'Воронин', 'user@yandex.ru', '','{noop}password');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 19105970),
  ('ROLE_ADMIN', 19105970);