DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (description, calories,
                   date_time, user_id) VALUES
  ('ужин', 1000, '2017-07-29 06:34:20', 100000),
  ('ужин2', 1200, '2017-07-29 07:34:20', 100000),
  ('завтрак', 1020, '2017-07-29 06:34:25', 100001);
