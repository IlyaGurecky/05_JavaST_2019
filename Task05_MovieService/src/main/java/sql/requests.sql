# Некоторые из запросов, которые будут использованы в DAO
USE `movie_service_db`;

# Прочиать все фильмы с сортировкой по порядку добавления
SELECT `f`.id,
       `f`.name,
       `f`.premier_date,
       `f`.description,
       `f`.image_path,
       `countries_catalog`.name  AS `country`,
       `categories_catalog`.name AS `catogory`
FROM `films` AS `f`
       LEFT OUTER JOIN categories_catalog
                       ON `f`.category_id = `categories_catalog`.id
       LEFT OUTER JOIN countries_catalog
                       ON f.country_id = countries_catalog.id
ORDER BY `f`.id DESC;

# Прочитать всех юзеров с сортировкой по ID
SELECT `users`.id,
       `users`.login,
       `users`.password,
       `user_info`.birth_date   AS `birth_date`,
       `user_info`.email        AS `email`,
       `user_info`.sex          AS `sex`,
       `countries_catalog`.name AS `country`
FROM `users`
       LEFT OUTER JOIN user_info
                       ON users.id = user_info.user_id
       LEFT OUTER JOIN countries_catalog
                       ON user_info.country_id = countries_catalog.id
ORDER BY `users`.id;

# Найти юзера по логину и паролю
SELECT `users`.id,
       `users`.role
FROM `users`
WHERE `users`.login = ?
  AND `users`.password = ?;

# Добавить фильм
INSERT INTO `films` (name, premier_date, country_id, category_id, image_path,
                     description)
VALUES (?, ?, ?, ?, ?, ?);

# Удалить фильм по ID
DELETE
FROM `films`
WHERE `films`.id = ?;

# Удалить юзера по  ID
DELETE
FROM `users`
WHERE `users`.id = ?;

# Обновить фильм
UPDATE `films`
SET name         = ?,
    premier_date = ?,
    description  = ?,
    image_path   = ?,
    country_id   = ?,
    category_id  = ?
WHERE id = ?;

# Обновить информацию о юзере
UPDATE `user_info`
SET email      = ?,
    sex        = ?,
    birth_date = ?,
    country_id = ?
WHERE user_id = ?;

# Добавить юзера
INSERT INTO `users` (login, password, role)
VALUES (?, ?, ?);

# Прочитать список фильмов в "Посмотреть позже" у определенного юзера
SELECT `see_later`.film_id,
       `films`.name,
       `films`.premier_date,
       `films`.description,
       `films`.image_path,
       `categories_catalog`.name AS `category`,
       `countries_catalog`.name  AS `country`
FROM `see_later`
       INNER JOIN `films`
                  ON see_later.film_id = `films`.id
       LEFT OUTER JOIN categories_catalog
                       ON films.category_id = categories_catalog.id
       LEFT OUTER JOIN countries_catalog
                       ON films.country_id = countries_catalog.id
WHERE user_id = ?;

# Прочитать список фильмов в "Просмотренные" у определенного юзера
SELECT `watched_films`.film_id,
       `watched_films`.viewing_date,
       `films`.name,
       `films`.premier_date,
       `films`.description,
       `films`.image_path,
       `categories_catalog`.name AS `category`,
       `countries_catalog`.name  AS `country`
FROM `watched_films`
       INNER JOIN `films`
                  ON watched_films.film_id = `films`.id
       LEFT OUTER JOIN categories_catalog
                       ON films.category_id = categories_catalog.id
       LEFT OUTER JOIN countries_catalog
                       ON films.country_id = countries_catalog.id
WHERE user_id = ?;

