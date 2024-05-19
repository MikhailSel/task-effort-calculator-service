CREATE TABLE IF NOT EXISTS tasks
(
    id   BIGSERIAL,
    name VARCHAR(200) NOT NULL UNIQUE,
    CONSTRAINT tasks_pkey_id PRIMARY KEY (id)
);

comment on column tasks.id is 'Task ID';
comment on column tasks.name is 'Task Name';

CREATE TABLE IF NOT EXISTS task_user_estimation
(
    task_id         BIGINT,
    user_id         BIGINT,
    days_per_person BIGINT,
    CONSTRAINT task_user_estimation_pkey_id PRIMARY KEY (task_id, user_id),
    CONSTRAINT fk_task_user_estimation_on_task FOREIGN KEY (task_id) REFERENCES tasks (id)
);

comment on column task_user_estimation.task_id is 'task ID';
comment on column task_user_estimation.user_id is 'user ID';
comment on column task_user_estimation.days_per_person is 'days per person';



