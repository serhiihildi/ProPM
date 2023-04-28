CREATE TABLE IF NOT EXISTS user (
                      id BIGINT NOT NULL PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS role (
                      id BIGINT NOT NULL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES user (id),
                           FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE IF NOT EXISTS project (
                         id BIGINT NOT NULL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS project_role (
                              project_id BIGINT NOT NULL,
                              role_id BIGINT NOT NULL,
                              PRIMARY KEY (project_id, role_id),
                              FOREIGN KEY (project_id) REFERENCES project (id),
                              FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE IF NOT EXISTS task (
                                    id BIGINT NOT NULL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    description VARCHAR(255) NOT NULL,
                                    created_date TIMESTAMP NOT NULL,
                                    updated_date TIMESTAMP NOT NULL,
                                    role_id BIGINT NOT NULL,
                                    project_id BIGINT NOT NULL,
                                    FOREIGN KEY (role_id) REFERENCES role (id),
                                    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE IF NOT EXISTS project_task (
                              project_id BIGINT NOT NULL,
                              task_id BIGINT NOT NULL,
                              PRIMARY KEY (project_id, task_id),
                              FOREIGN KEY (project_id) REFERENCES project (id),
                              FOREIGN KEY (task_id) REFERENCES task (id)
);
