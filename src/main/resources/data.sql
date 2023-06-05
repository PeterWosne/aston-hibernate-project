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

CREATE TABLE IF NOT EXISTS positions(id bigserial PRIMARY KEY,title VARCHAR(255) UNIQUE NOT NULL,created_at timestamp,updated_at timestamp);
CREATE TABLE employees(id bigserial PRIMARY KEY,name VARCHAR(255) NOT NULL,position_id bigint NOT NULL references positions(id),created_at timestamp,updated_at timestamp);
ALTER TABLE employees ADD CONSTRAINT FK_positions FOREIGN KEY(position_id) REFERENCES positions(id) ON DELETE CASCADE ON UPDATE CASCADE;