CREATE TABLE IF NOT EXISTS roles
(
    id   BIGSERIAL,
    name VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT roles_pkey_id PRIMARY KEY (id)
);

comment on column roles.id is 'Role ID';
comment on column roles.name is 'Role Name';

ALTER TABLE users ADD COLUMN id_role BIGINT;
ALTER TABLE users DROP COLUMN role;

ALTER TABLE users
ADD CONSTRAINT fk_constraint_role FOREIGN KEY (id_role) REFERENCES roles(id);

INSERT INTO roles (name) VALUES ('аналитик');
INSERT INTO roles (name) VALUES ('разработчик Backend');
INSERT INTO roles (name) VALUES ('разработчик FrontEnd');
INSERT INTO roles (name) VALUES ('тестировщик');

