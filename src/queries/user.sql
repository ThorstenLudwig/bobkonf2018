-- name: create-user-table!
  CREATE TABLE users
  (
    id VARCHAR(255), -- SERIAL für fortlaufende id
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR (255),
  );

-- name: drop-table!
DROP TABLE IF EXISTS users;

-- name: create-user!
INSERT INTO users (
  id,
  username,
  password,
  email
) VALUES (
  :id,
  :username,
  :password,
  :email
);

-- name: get-user
SELECT * FROM users WHERE id = :id;

-- name: delete-user!
DELETE FROM users WHERE id = :id;

-- name: update-user!
UPDATE users SET username = :username, password = :password, email = :email WHERE id = :id;

-- name: all-user
SELECT * FROM users;