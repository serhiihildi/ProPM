INSERT INTO role (id, name, description) VALUES (1, 'ADMIN', 'Admin role');
INSERT INTO role (id, name, description) VALUES (2, 'USER', 'User role');

INSERT INTO project (id, name, description) VALUES (1, 'Project 1', 'Project description 1');
INSERT INTO project (id, name, description) VALUES (2, 'Project 2', 'Project description 2');

INSERT INTO user (id, first_name, last_name, email, password)
VALUES (1, 'John', 'Doe', 'john.doe@example.com', '$2a$10$Z0Wd8vO1hw0Xg4SmfrxGeurW8cLd0A5S0FtrjZw5zqI5gm5Q9XGnK'); -- password is 'password'
INSERT INTO user (id, first_name, last_name, email, password)
VALUES (2, 'Jane', 'Doe', 'jane.doe@example.com', '$2a$10$nSY32wKjywzPNsBwpX9O7e/zUV/XBI0V7NGVAJhOcL7sCVQZzKs7m'); -- password is 'password'

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO task (id, name, description, created_date, updated_date, role_id, project_id) VALUES (1, 'Task 1', 'Task description 1', '2023-04-27 10:30:00', '2023-04-27 11:30:00', 1, 1);
INSERT INTO task (id, name, description, created_date, updated_date, role_id, project_id) VALUES (2, 'Task 2', 'Task description 2', '2023-04-27 11:30:00', '2023-04-27 12:30:00', 2, 1);
