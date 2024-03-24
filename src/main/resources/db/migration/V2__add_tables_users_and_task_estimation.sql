CREATE TABLE IF NOT EXISTS users
(
id BIGSERIAL,
fio VARCHAR(100) NOT NULL UNIQUE,
CONSTRAINT users_pkey_id PRIMARY KEY (id)
);

comment on column users.id is 'user ID';
comment on column users.fio is 'user Fio';

CREATE TABLE IF NOT EXISTS task_estimation
(
task_id BIGINT,
user_id BIGINT,
days_per_person BIGINT,
CONSTRAINT estimation_pkey_id PRIMARY KEY (task_id, user_id),
CONSTRAINT fk_estimation_on_task FOREIGN KEY (task_id) REFERENCES tasks (id),
CONSTRAINT fk_estimation_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);


