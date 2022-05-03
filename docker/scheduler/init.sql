CREATE TABLE commands(
     id serial NOT NULL, -- уникальный номер записи
     command VARCHAR NOT NULL, -- команда для выполнения
     type VARCHAR NOT NULL, -- тип команды
     regularity BIGINT NOT NULL, -- регулярность выполнения в секундах
     start BIGINT NOT NULL, -- время начала работы "UNIX_TIME"
     owner VARCHAR NOT NULL, -- кто создал задачу
     time TIMESTAMP NOT NULL DEFAULT now(),  -- время создания задачи
     PRIMARY KEY (id)
);

INSERT INTO commands (command, type, regularity, start, owner)
VALUES ('ping yandex.ru', 'cmd', 60, 1643300220, 'Ne_kit');

CREATE TABLE log(
    id serial NOT NULL, -- уникальный номер записи
    command VARCHAR NOT NULL, -- команда для выполнения
    result BOOLEAN NOT NULL, -- результат
    message VARCHAR NOT NULL, -- сообщение
    owner VARCHAR NOT NULL, -- кто создал задачу
    start TIMESTAMP NOT NULL, -- время начала задачи
    duration BIGINT NOT NULL,  -- время длит-ти задачи (в сек)
    PRIMARY KEY (id)
);