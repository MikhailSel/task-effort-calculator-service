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
