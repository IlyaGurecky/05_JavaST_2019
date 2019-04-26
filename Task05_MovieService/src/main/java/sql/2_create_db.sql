CREATE DATABASE `movie_service_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE ON `movie_service_db`.*
  TO 'movie_service_user'@'localhost';