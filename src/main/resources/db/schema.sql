CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    first_name VARCHAR(255) NOT NULL,
                                    last_name VARCHAR(255) NOT NULL,
                                    email VARCHAR(255) UNIQUE NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    name VARCHAR(255) NOT NULL,
                                    description VARCHAR(255) NOT NULL,
                                    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_role (
                                         user_id BIGINT NOT NULL,
                                         role_id BIGINT NOT NULL,
                                         PRIMARY KEY (user_id, role_id),
                                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                                         FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS project (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       name VARCHAR(255) NOT NULL,
                                       description VARCHAR(255) NOT NULL,
                                       PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS project_role (
                                            project_id BIGINT NOT NULL,
                                            role_id BIGINT NOT NULL,
                                            PRIMARY KEY (project_id, role_id),
                                            FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
                                            FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS task (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    name VARCHAR(255) NOT NULL,
                                    description VARCHAR(255) NOT NULL,
                                    created_date DATETIME NOT NULL,
                                    updated_date DATETIME NOT NULL,
                                    role_id BIGINT NOT NULL,
                                    project_id BIGINT NOT NULL,
                                    PRIMARY KEY (id),
                                    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
                                    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);

DROP INDEX IF EXISTS idx_task_project_role ON task;
CREATE INDEX idx_task_project_role ON task (project_id, role_id);

