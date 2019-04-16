USE `movie_service_db`;

CREATE TABLE `users`
(
  `id`       INTEGER      NOT NULL AUTO_INCREMENT,
  `login`    VARCHAR(255) NOT NULL UNIQUE,
  `password` CHAR(32)     NOT NULL,
  /*
   * 0 - администратор (Role.ADMIN)
   * 1 - редактор (Role.EDITOR)
   * 2 - пользователь (Role. USER)
   */
  `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
  PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
  `id`      INTEGER NOT NULL AUTO_INCREMENT,
  `email`   VARCHAR(255) UNIQUE,
  `sex`     VARCHAR(6),
  `age`     TINYINT,
  `country` VARCHAR(100),
  `user_id` INTEGER NOT NULL UNIQUE,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `films`
(
  `id`           INTEGER      NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255) NOT NULL,
  `premier_date` DATE         NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `category`
(
  `id`   INTEGER      NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `film_info`
(
  `id`          INTEGER NOT NULL AUTO_INCREMENT,
  `film_id`     INTEGER NOT NULL UNIQUE,
  `country`     VARCHAR(50),
  `producer`    VARCHAR(100),
  `actors`      TEXT,
  `category_id` INTEGER NOT NULL,
  `image`       VARCHAR(100),
  `description` TEXT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`film_id`)
    REFERENCES `films` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

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
    ON DELETE CASCADE
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

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
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;
