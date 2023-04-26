-- Добавляем пользователей
INSERT INTO users (first_name, last_name, email, password) VALUES
                                                                        ('Иван', 'Иванов', 'ivanov@mail.com', '123456'),
                                                                        ('Петр', 'Петров', 'petrov@mail.com', 'qwerty'),
                                                                        ('Сидор', 'Сидоров', 'sidorov@mail.com', 'secret');

-- Добавляем проекты
INSERT INTO projects (name, description, manager_id, start_date, end_date) VALUES
                                                                               ('Проект 1', 'Описание проекта 1', 1, '2023-01-01', '2023-03-31'),
                                                                               ('Проект 2', 'Описание проекта 2', 2, '2023-02-01', '2023-04-30'),
                                                                               ('Проект 3', 'Описание проекта 3', 3, '2023-03-01', '2023-05-31');

-- Добавляем команды
INSERT INTO teams (name, project_id, manager_id) VALUES
                                                                  ('Команда 1', 'Описание команды 1', 1, 1),
                                                                  ('Команда 2', 'Описание команды 2', 2, 2),
                                                                  ('Команда 3', 'Описание команды 3', 3, 3);

-- Добавляем связи между проектами и разработчиками
INSERT INTO project_developers (project_id, developer_id) VALUES
                                                              (1, 1),
                                                              (1, 2),
                                                              (2, 2),
                                                              (2, 3),
                                                              (3, 1),
                                                              (3, 3);
