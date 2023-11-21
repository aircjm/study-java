create table sys_config
(
    id          bigint auto_increment
        primary key,
    config_name char(255) default '' not null,
    config_json json                 null,
    deleted     int                  null
)
    comment 'system config';

create table sys_log
(
    id            bigint    null,
    log_serial    char(255) null,
    request_json  json      null,
    response_json json      null,
    deleted       int       null
);

create table sys_user
(
    id    bigint      not null
        primary key,
    name  varchar(30) null,
    age   int         null,
    email varchar(50) null
);


