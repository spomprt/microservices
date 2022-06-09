/*
    id            | uuid                   | not null
    aggregatetype | character varying(255) | not null
    aggregateid   | character varying(255) | not null
    type          | character varying(255) | not null
    payload       | jsonb                  |
 */


create table command
(
    id            uuid         not null,
    aggregatetype varchar(255) not null,
    aggregateid   varchar(255) not null,
    type          varchar(255) not null,
    payload       jsonb
);