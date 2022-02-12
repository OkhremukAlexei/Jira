drop table if exists account;
drop table if exists comment;
drop table if exists task;
drop table if exists project;
drop table if exists user_roles;
drop table if exists roles;
drop table if exists user_team;
drop table if exists team;
drop table if exists users;

create table if not exists users
(
    id bigint not null auto_increment,
    login varchar(255),
    password varchar(255),
    primary key (id)
);

create table account
(
    user_id bigint not null,
    email varchar(255),
    name varchar(255),
    surname varchar(255),
    primary key (user_id)
);

create table if not exists comment
(
    id integer not null auto_increment,
     text varchar(2047),
      primary key (id)
);

create table if not exists team
(
    id bigint not null auto_increment,
    number_of_persons integer not null,
    primary key (id)
);

create table if not exists project
(
id bigint not null auto_increment,
link_to_git varchar(255),
name varchar(255),
progress integer not null,
team_id bigint,
 primary key (id)
);

create table if not exists roles
(
    id bigint not null auto_increment,
    name varchar(20),
    primary key (id)
);


create table if not exists task
(
    id integer not null auto_increment,
    date_time datetime(6),
    description varchar(255),
    spent_time datetime(6),
    status varchar(255),
    title varchar(255),
    project_id bigint not null,
    user_id bigint not null,
    primary key (id)
);

create table if not exists user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

create table if not exists user_team
(
    team_id bigint not null,
    user_id bigint not null
);

alter table users
    add constraint UKow0gan20590jrb00upg3va2fn unique (login);

alter table account
    add constraint FKra7xoi9wtlcq07tmoxxe5jrh4 foreign key (user_id) references users (id);

alter table project
    add constraint FK99hcloicqmg95ty11qht49n8x foreign key (team_id) references team (id);

alter table task
    add constraint FKk8qrwowg31kx7hp93sru1pdqa foreign key (project_id) references project (id);

alter table task
    add constraint FK32eeu8p13crqmo7dfdtn6hncm foreign key (user_id) references users (id);

alter table user_roles
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles (id);

alter table user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users (id);

alter table user_team
    add constraint FKmodbby1xpn7sf5rmw7f81n0v4 foreign key (user_id) references users (id);

alter table user_team
    add constraint FK6d6agqknw564xtsa91d3259wu foreign key (team_id) references team (id);
