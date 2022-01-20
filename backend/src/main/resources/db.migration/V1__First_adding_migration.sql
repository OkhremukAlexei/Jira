drop table if exists account;
drop table if exists comment;
drop table if exists task_user;
drop table if exists task;
drop table if exists project;
drop table if exists user_roles;
drop table if exists roles;
drop table if exists user_team;
drop table if exists team;
drop table if exists users;

create table if not exists users
(
    id       serial
        constraint users_pkey
            primary key,
    login    varchar(255)
        constraint ukow0gan20590jrb00upg3va2fn
            unique,
    password varchar(255)
);

create table if not exists account
(
    user_id serial not null
        constraint account_pkey
            primary key
        constraint fkra7xoi9wtlcq07tmoxxe5jrh4
            references users,
    email   varchar(255),
    name    varchar(255),
    surname varchar(255)
);

create table if not exists comment
(
    id   serial
        constraint comment_pkey
            primary key,
    text varchar(255)
);

create table if not exists team
(
    id                serial
        constraint team_pkey
            primary key,
    number_of_persons integer not null
);

create table if not exists project
(
    id          serial
        constraint project_pkey
            primary key,
    link_to_git varchar(255),
    name        varchar(255),
    progress    integer not null,
    team_id     bigint
        constraint fk99hcloicqmg95ty11qht49n8x
            references team
);

create table if not exists roles
(
    id   serial
        constraint roles_pkey
            primary key,
    name varchar(20)
);


create table if not exists task
(
    id          serial
        constraint task_pkey
            primary key,
    date_time   timestamp,
    description varchar(255),
    spent_time  timestamp,
    status      varchar(255),
    title       varchar(255),
    project_id  bigint not null
        constraint fkk8qrwowg31kx7hp93sru1pdqa
            references project
);

create table if not exists task_user
(
    task_id serial not null
        constraint fkd1fn28rqhh1ku21jx7hcksvh9
            references task,
    user_id bigint  not null
        constraint fk32eeu8p13crqmo7dfdtn6hncm
            references users,
    constraint task_user_pkey
        primary key (task_id, user_id)
);



create table if not exists user_roles
(
    user_id serial not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id bigint not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

create table if not exists user_team
(
    team_id serial not null
        constraint fk6d6agqknw564xtsa91d3259wu
            references team,
    user_id serial not null
        constraint fkmodbby1xpn7sf5rmw7f81n0v4
            references users
);


