create table account
(
    username   varchar,
    first_name varchar,
    last_name  varchar,
    bank       bigint default 0,
    primary key (username)
);