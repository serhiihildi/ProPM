INSERT INTO project (name, description)
VALUES ('Project A', 'Description of Project A'),
       ('Project B', 'Description of Project B'),
       ('Project C', 'Description of Project C');

INSERT INTO role (name, description, project_id)
VALUES ('Admin', 'Admin role for Project A', 1),
       ('User', 'User role for Project A', 1),
       ('Admin', 'Admin role for Project B', 2),
       ('User', 'User role for Project B', 2),
       ('Admin', 'Admin role for Project C', 3),
       ('User', 'User role for Project C', 3);

INSERT INTO project_roles (project_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);

INSERT INTO user (first_name, last_name, email, password)
VALUES ('John', 'Doe', 'john.doe@example.com', 'password123'),
       ('Jane', 'Doe', 'jane.doe@example.com', 'password123'),
       ('Bob', 'Smith', 'bob.smith@example.com', 'password123'),
       ('Alice', 'Johnson', 'alice.johnson@example.com', 'password123');

INSERT INTO user_role (role_id, user_id)
VALUES (1, 1),
       (2, 2),
       (1, 3),
       (2, 4);

INSERT INTO task (name, description, status, creator_id, assignee_id, project_id, role_id, created_date, updated_date)
VALUES ('Task 1', 'Description of Task 1', 'OPEN', 1, NULL, 1, 1, NOW(), NULL),
       ('Task 2', 'Description of Task 2', 'IN_PROGRESS', 2, 1, 1, 2, NOW(), NULL),
       ('Task 3', 'Description of Task 3', 'OPEN', 3, NULL, 2, 3, NOW(), NULL),
       ('Task 4', 'Description of Task 4', 'IN_PROGRESS', 4, 2, 2, 4, NOW(), NULL),
       ('Task 5', 'Description of Task 5', 'DONE', 1, NULL, 3, 5, NOW(), NULL),
       ('Task 6', 'Description of Task 6', 'IN_PROGRESS', 2, 3, 3, 6, NOW(), NULL);

INSERT INTO project_users (project_id, user_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 4);