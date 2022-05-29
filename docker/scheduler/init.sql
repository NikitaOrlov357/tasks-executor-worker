DROP TABLE IF EXISTS log;
DROP TABLE IF EXISTS command;
DROP TABLE IF EXISTS command_type;
DROP TABLE IF EXISTS owner;

CREATE TABLE command_type
(
    id SERIAL NOT NULL PRIMARY KEY, --уникальный номер записи
    type VARCHAR NOT NULL -- тип команды,
);

CREATE TABLE owner
(
    id SERIAL NOT NULL PRIMARY KEY, --уникальный номер записи
    name VARCHAR NOT NULL -- имя пользователя
);

CREATE TABLE command
(
    id serial NOT NULL PRIMARY KEY, -- уникальный номер записи
    command VARCHAR NOT NULL, -- команда для выполнения
    type_id INT NOT NULL REFERENCES command_type (id), -- id типа команды
    regularity BIGINT NOT NULL, -- регулярность выполнения в милисекундах
    start BIGINT NOT NULL, -- время начала работы "UNIX_TIME"
    owner_id INT NOT NULL REFERENCES owner (id), -- кто создал задачу
    time TIMESTAMP NOT NULL DEFAULT now()  -- время создания задачи
);

CREATE TABLE log(
    id serial NOT NULL PRIMARY KEY, -- уникальный номер записи
    command_id INT NOT NULL REFERENCES command(id), -- команда для выполнения
    result BOOLEAN NOT NULL, -- результат
    message VARCHAR NOT NULL, -- сообщение
    owner VARCHAR NOT NULL, -- кто создал задачу
    start TIMESTAMP NOT NULL, -- время начала задачи
    duration BIGINT NOT NULL  -- время длит-ти задачи (в милисек)
);

INSERT INTO command_type (type) VALUES ('cmd');
INSERT INTO owner (name) VALUES ('Ne_kit');
INSERT INTO command (command, type_id, regularity, start, owner_id)
VALUES ('ping yandex.ru', 1, 60, 1643300220, 1);