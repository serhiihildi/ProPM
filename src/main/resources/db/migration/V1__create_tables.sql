CREATE TABLE users (
                       id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE projects (
                          id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          manager_id BIGINT NOT NULL,
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL,
                          CONSTRAINT projects_manager_id_fk FOREIGN KEY (manager_id) REFERENCES users (id)
);

CREATE TABLE teams (
                       id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       description VARCHAR(255) NOT NULL,
                       project_id BIGINT NOT NULL,
                       manager_id BIGINT NOT NULL,
                       CONSTRAINT teams_project_id_fk FOREIGN KEY (project_id) REFERENCES projects (id),
                       CONSTRAINT teams_manager_id_fk FOREIGN KEY (manager_id) REFERENCES users (id)
);

CREATE TABLE project_developers (
                                    project_id BIGINT NOT NULL,
                                    developer_id BIGINT NOT NULL,
                                    CONSTRAINT project_developers_project_id_fk FOREIGN KEY (project_id) REFERENCES projects (id),
                                    CONSTRAINT project_developers_developer_id_fk FOREIGN KEY (developer_id) REFERENCES users (id),
                                    CONSTRAINT project_developers_pk PRIMARY KEY (project_id, developer_id)
);
