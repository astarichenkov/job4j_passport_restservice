create table if not exists passports
(
    id       bigserial primary key,
    series   int,
    num bigserial,
    release_date timestamp,
    firstname varchar(255),
    lastname varchar(255)
    );

create table if not exists address
(
    id       bigserial primary key,
    apartment varchar(255),
    city varchar(255),
    house varchar(255),
    street varchar(255)
);
--
-- create table if not exists url_shortcut
-- (
--     id      bigserial
--     constraint url_shortcut_pkey
--     primary key,
--     code    varchar(255),
--     count   bigint       not null,
--     url     varchar(255) not null,
--     site_id bigint
--     constraint fkme529jd16uaewkrdfr3fu2duj
--     references sites
--     );
--
