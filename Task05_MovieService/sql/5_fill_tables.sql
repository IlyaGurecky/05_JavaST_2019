USE `movie_service_db`;

INSERT INTO `users` (login, password, role)
VALUES ('user1',
        '$2a$10$dtg6L2dmLBzNEdHQyCIATu/PPUvpci1X1HrCSCrKSQrecPa8y/oi6', # BCrypt hash ("user1")
        2),
       ('user2',
        '$2a$10$nIA71sgtHMIuLCxcWE6eZeY15bbKlXV3kJiRNjOdb38AuPKlbejDK', # BCrypt hash ("user2")
        2),
       ('editor1',
        '$2a$10$nf2jShTR6ZkZecgxxdzzguDh.2e3Y5uFoLsEhrl62w9FV9Dl4oqf2', # BCrypt hash ("editor1")
        1);

INSERT INTO `categories_catalog` (name)
VALUES ('Комедия'),
       ('Ужасы'),
       ('Фэнтези'),
       ('Боевик'),
       ('Семейные'),
       ('Детективы'),
       ('Драмы');

INSERT INTO `countries_catalog`(name)
VALUES ('США'),
       ('Великобритания'),
       ('Россия'),
       ('Новая Зеландия'),
       ('Австралия'),
       ('Испания'),
       ('Франция'),
       ('Германия'),
       ('Китай'),
       ('Беларусь'),
       ('Украина'),
       ('Польша');

INSERT INTO `films` (id, name, premier_date, country_id, category_id,
                     image_path, description)
VALUES (1, 'Зеленая миля', '1999-12-06', 1, 7, 'greenMile.jpg', 'Режиссер: Фрэнк
 Дарабонт. Картина снята по одноименному роману Стивена Кинга. Однажды в
блок для приговоренных к смертной казни поступает новый заключенный –
Джон Коффи, осужденный за убийство маленьких девочек. Коффи привлекает внимание
начальника этого блока – Пола Эджкомба. За долгие годы работы он насмотрелся на
разных преступников, и он немного разбирается в людях. Пол сомневается в
виновности Коффи, ведь несмотря на его устрашающий вид, он обладает добротой и
человечностью. Так же со временем выясняется, что Джон является обладателем
 удивительного дара – способностью'),
       (2, 'Первому игроку приготовиться', '2018-03-11', 1, 3,
        'firstPlayer.jpg', 'Режиссер: Стивен Спилберг. Действие киноленты
        разворачивается в недалеком будущем – в 2045-ом году. Наш мир, каким мы
         его знали, пришел к моменту коллапса и полного хаоса. Единственной
         отрадой для людей, живущих в развалинах, ставших для многих домом,
         становится культовая игра под названием «Оазис», являющая собой
         насыщенный и полный приключений виртуальный мир, в который многие
         сбегают из мрачной реальности.'),
       (3, 'Властелин колец: Братство кольца', '2001-12-10', 4, 3,
        'lordOfTheRing.jpg', NULL),
       (4, 'Властелин колец: Две крепости', '2002-12-05', 4, 3, 'twoCastles.jpg', NULL),
       (5, 'Властелин колец: Возвращение Короля', '2003-12-01', 4, 3, 'LORKingBack.jpg',
        NULL),
       (6, 'Гарри Поттер и философский камень', '2001-11-04', 2, 3,
        'harryPotter.jpg', 'Режиссер: Крис Коламбус. После того, как родители
        Гарри погибли, его воспитанием занимаются дядя и тетя. В свой
        одиннадцатый день рождения он узнает, что является самым настоящим
        волшебником, и его приглашают учиться в Хогвартскую школу магии. Именно
        там, от одного из преподавателей, Гарри узнает истинную правду о своем
        прошлом, и что его родители на самом деле были убиты лордом
        Волан-де-Мортом – сильнейшим темным магом, из когда либо существовавших.'),
       (7, 'Гарри Поттер и Тайная комната', '2002-03-11', 2, 3, 'harryPotterAndChamberOfSecrets.jpg', NULL),
       (8, 'Назад в будущее', '1985-07-03', 2, 3, 'backToTheFuture.jpg', NULL),
       (9, 'Мальчишник в Вегасе', '2009-05-30', 1, 1, 'malchishnik.jpg', NULL),
       (10, 'Одноклассники', '2010-06-24', 1, 1, 'classmates.jpg', 'Режиссер: Деннис Дуган.
       Сюжет комедийного фильма рассказывает о том, как пятеро школьных друзей
       спустя тридцать лет встречаются, чтобы проводить в последний путь своего
       любимого тренера по баскетболу. После похорон они отправляются вместе со
       своими семьями в домик у озера, чтобы отдохнуть и вспомнить былые
       времена. Прибыв на место, уже состоявшиеся в жизни мужики и отцы,
       превращаются в самых настоящих подростков.'),
       (11, 'Иван Васильевич меняет профессию', '1973-09-17', NULL, 1,
        'ivanVas.jpg', NULL),
       (12, 'Чёрная дыра', '2000-02-18', 1, 4, 'blackHole.jpg', ' Режиссер: Дэвид Туи.
       Действия фильма разворачиваются в далеком будущем. Космическому кораблю,
       перевозящему особо опасного преступника Ричарда Б. Риддика из одной
       тюрьмы в другую, на своем пути приходится пролететь сквозь хвост кометы.
       Однако во время этого манёвра судно получает ряд серьезных повреждений,
       в результате чего капитан погибает, а второму пилоту приходится искать
       планету для экстренной посадки.');

INSERT INTO `see_later` (user_id, film_id, added_date)
VALUES (2, 1, '2019-04-10'),
       (3, 3, '2019-04-12'),
       (3, 10, '2019-04-12'),
       (3, 6, '2019-04-13'),
       (3, 7, '2019-04-13'),
       (2, 12, '2019-04-15'),
       (2, 6, '2019-04-15');

INSERT INTO `watched_films` (user_id, film_id, viewing_date)
VALUES (2, 1, '2019-04-12'),
       (3, 8, '2019-04-15'),
       (3, 11, '2019-04-09'),
       (2, 2, '2019-04-07');

INSERT INTO `user_info` (email, sex, birth_date, country_id, user_id)
VALUES ('andrey13@mail.ru', 'м', '1994-12-08', 10, 2),
       ('lora11@gmail.com', 'ж', NULL, 3, 3),
       ('oracle@gmail.com', 'м', NULL, 3, 1),
       ('gleb@gmail.com', 'м', '1992-03-18', 3, 4);
