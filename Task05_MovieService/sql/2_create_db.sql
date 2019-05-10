CREATE DATABASE `movie_service_db` DEFAULT CHARACTER SET utf8;

CREATE USER IF NOT EXISTS 'movie_service_user'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE ON `movie_service_db`.*
  TO 'movie_service_user'@'localhost';