USE `movie_service_db`;

CREATE TABLE `users`
(
  `id`       INTEGER      NOT NULL AUTO_INCREMENT,
  `login`    VARCHAR(32)  NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  /*
   * 0 - администратор (Role.ADMIN)
   * 1 - редактор (Role.EDITOR)
   * 2 - пользователь (Role. USER)
   */
  `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
  PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8;

CREATE TABLE `countries_catalog`
(
  `id`   INTEGER      NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
  `user_id`    INTEGER NOT NULL UNIQUE,
  `email`      VARCHAR(50) UNIQUE,
  `sex`        CHAR(1) CHECK (`sex` IN ('м', 'ж')),
  `birth_date` DATE,
  `country_id` INTEGER,
  FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (`country_id`)
    REFERENCES `countries_catalog` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
) DEFAULT CHARACTER SET utf8;

CREATE TABLE `categories_catalog`
(
  `id`   INTEGER      NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8;

CREATE TABLE `films`
(
  `id`           INTEGER      NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255) NOT NULL,
  `premier_date` DATE         NOT NULL,
  `country_id`   INTEGER,
  `category_id`  INTEGER,
  `image_path`   VARCHAR(100),
  `description`  TEXT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`)
    REFERENCES `categories_catalog` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (`country_id`)
    REFERENCES `countries_catalog` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) DEFAULT CHARACTER SET utf8;

CREATE TABLE `see_later`
(
  `id`         INTEGER NOT NULL AUTO_INCREMENT,
  `user_id`    INTEGER NOT NULL,
  `film_id`    INTEGER NOT NULL,
  `added_date` DATE    NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (`film_id`)
    REFERENCES `films` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  UNIQUE (user_id, film_id)
) DEFAULT CHARACTER SET utf8;

CREATE TABLE `watched_films`
(
  `id`           INTEGER NOT NULL AUTO_INCREMENT,
  `user_id`      INTEGER NOT NULL,
  `film_id`      INTEGER NOT NULL,
  `viewing_date` DATE    NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (`film_id`)
    REFERENCES `films` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
) DEFAULT CHARACTER SET utf8;
