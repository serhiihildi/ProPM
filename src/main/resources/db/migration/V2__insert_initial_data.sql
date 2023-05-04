INSERT INTO project (name, description) VALUES ('Project 1', 'Description of Project 1');
INSERT INTO project (name, description) VALUES ('Project 2', 'Description of Project 2');

INSERT INTO project_roles (project_id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO project_roles (project_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO project_roles (project_id, role) VALUES (2, 'ROLE_USER');

INSERT INTO role (name, description, project_id) VALUES ('ROLE_ADMIN', 'Administrator', 1);
INSERT INTO role (name, description, project_id) VALUES ('ROLE_USER', 'Regular user', 1);
INSERT INTO role (name, description, project_id) VALUES ('ROLE_USER', 'Regular user', 2);

INSERT INTO task (name, description, project_id, role_id, created_date) VALUES ('Task 1', 'Description of Task 1', 1, 1, NOW());
INSERT INTO task (name, description, project_id, role_id, created_date) VALUES ('Task 2', 'Description of Task 2', 1, 2, NOW());

INSERT INTO user (first_name, last_name, email, password) VALUES ('John', 'Doe', 'john.doe@example.com', 'password');
INSERT INTO user (first_name, last_name, email, password) VALUES ('Jane', 'Doe', 'jane.doe@example.com', 'password');

INSERT INTO user_role (role_id, user_id) VALUES (1, 1);
INSERT INTO user_role (role_id, user_id) VALUES (2, 1);
INSERT INTO user_role (role_id, user_id) VALUES (2, 2);