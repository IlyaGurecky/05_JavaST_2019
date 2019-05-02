USE `movie_service_db`;
INSERT INTO `users` (`id`, `login`, `password`, `role`)
VALUES (1, 'admin',
        '$2a$10$2ZB8zu9qtFMTL5PnKujDlOxQlE2ssZT6wJ/57eUgNEyXvxtMJ5MH6', # BCrypt hash ("admin")
        0);
