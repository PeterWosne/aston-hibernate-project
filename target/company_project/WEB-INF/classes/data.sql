CREATE TABLE IF NOT EXISTS positions(
    id             bigserial PRIMARY KEY,
    title          VARCHAR(255) UNIQUE NOT NULL,
    created_at     timestamp current_timestamp,
    updated_at     timestamp current_timestamp
);

CREATE TABLE employees(
   id            bigserial PRIMARY KEY,
   name          VARCHAR(255) NOT NULL,
   position_id   serial NOT NULL references positions(id),
   created_at    timestamp,
   updated_at    timestamp
);

CREATE TABLE projects(
   id             bigserial PRIMARY KEY,
   title          VARCHAR(255) UNIQUE NOT NULL,
   created_at     timestamp current_timestamp,
   updated_at     timestamp current_timestamp
);

CREATE TABLE projects2employees(
   project_id BIGINT NOT NULL,
   employee_id BIGINT NOT NULL,
   CONSTRAINT pk_pro2empl PRIMARY KEY (project_id,employee_id),
   CONSTRAINT fk_1 FOREIGN KEY (employee_id) REFERENCES employees(id),
   CONSTRAINT fk_2 FOREIGN KEY (project_id) REFERENCES projects(id)
);


