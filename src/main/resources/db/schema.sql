create table if not exists passports
(
    id           bigserial
    constraint passports_pkey
    primary key,
    series       integer,
    number          bigserial,
    release_date timestamp,
    firstname    varchar(255),
    lastname     varchar(255),
    birth_date   date,
    expire_date  date,
    unique(series, number)
    );

create table if not exists address
(
    id        bigserial
    constraint address_pkey
    primary key,
    apartment varchar(255),
    city      varchar(255),
    house     varchar(255),
    street    varchar(255)
    );