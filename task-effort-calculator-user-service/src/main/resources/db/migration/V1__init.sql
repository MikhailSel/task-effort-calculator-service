CREATE TABLE IF NOT EXISTS roles
(
    id   BIGSERIAL,
    name VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT roles_pkey_id PRIMARY KEY (id)
);

comment on column roles.id is 'Role ID';
comment on column roles.name is 'Role Name';

INSERT INTO roles (name)
VALUES ('Analytic');
INSERT INTO roles (name)
VALUES ('BackDev');
INSERT INTO roles (name)
VALUES ('FrontDev');
INSERT INTO roles (name)
VALUES ('Tester');


CREATE TABLE IF NOT EXISTS users
(
    id      BIGSERIAL,
    fio     VARCHAR(100) NOT NULL UNIQUE,
    email   VARCHAR(100) NOT NULL UNIQUE,
    role_id BIGINT       NOT NULL,
    CONSTRAINT fk_constraint_role FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT users_pkey_id PRIMARY KEY (id)
);

comment on column users.id is 'user ID';
comment on column users.fio is 'user Fio';


CREATE TABLE IF NOT EXISTS vocation_period
(
    id        BIGSERIAL,
    user_id   BIGINT NOT NULL,
    date_from DATE   NOT NULL,
    date_to   DATE   NOT NULL,
    CONSTRAINT vocation_period_pkey_id PRIMARY KEY (id),
    CONSTRAINT fk_vocation_period_on_users FOREIGN KEY (user_id) REFERENCES users (id)
);

comment on table vocation_period is 'Таблица периодов отпусков';
comment on column vocation_period.id is 'Идентификатор периода отпуска';
comment on column vocation_period.user_id is 'Идентификатор пользователя';
comment on column vocation_period.date_from is 'Дата начала отпуска';
comment on column vocation_period.date_to is 'Дата окончания отпуска';


