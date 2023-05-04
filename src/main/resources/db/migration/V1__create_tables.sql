CREATE TABLE project
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_project PRIMARY KEY (id)
);

CREATE TABLE project_roles
(
    project_id BIGINT       NOT NULL,
    `role`     VARCHAR(255) NULL
);

ALTER TABLE project_roles
    ADD CONSTRAINT fk_project_roles_on_project FOREIGN KEY (project_id) REFERENCES project (id);

CREATE TABLE IF NOT EXISTS `role`
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    project_id    BIGINT                NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

ALTER TABLE `role`
    ADD CONSTRAINT FK_ROLE_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

CREATE TABLE task
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    project_id    BIGINT                NOT NULL,
    role_id       BIGINT                NOT NULL,
    created_date  datetime              NOT NULL,
    updated_date  datetime              NULL,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255)          NOT NULL,
    last_name  VARCHAR(255)          NOT NULL,
    email      VARCHAR(255)          NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES user (id);
