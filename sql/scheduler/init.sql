CREATE DATABASE scheduler;

CREATE TABLE commands(
    id         serial    NOT NULL, -- уникальный номер записи
    command    VARCHAR   NOT NULL, -- команда для выполнения
    type       VARCHAR   NOT NULL, -- тип команды
    regularity BIGINT    NOT NULL, -- регулярность выполнения в секундах
    start      BIGINT    NOT NULL, -- время начала работы "UNIX_TIME"
    trigger    VARCHAR   NOT NULL, -- кто создал задачу
    time       TIMESTAMP NOT NULL,  -- время создания задачи
    PRIMARY KEY (ID)
)