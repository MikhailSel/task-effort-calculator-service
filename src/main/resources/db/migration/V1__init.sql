CREATE TABLE IF NOT EXISTS tasks
(
    id   BIGSERIAL,
    name VARCHAR(200) NOT NULL UNIQUE,
    CONSTRAINT tasks_pkey_id PRIMARY KEY (id)
);

comment on column tasks.id is 'Task ID';
comment on column tasks.name is 'Task Name';


