ALTER table comment ALTER COLUMN text TYPE varchar(2047);

--ROLES
INSERT into roles (name) values ('ROLE_ADMIN');
INSERT into roles (name) values ('ROLE_MANAGER');
INSERT into roles (name) values ('ROLE_USER');

--First user/admin
INSERT into users (login, password) values ('1','1111');
INSERT into account (user_id,email, name, surname) values (1,'1111@gmail.com','First','1USER');
INSERT into comment (text) values ('Comment of first user');
INSERT into user_roles values (1,1);

--Second user
INSERT into users (login, password) values ('2','2222');
INSERT into account (user_id, email, name, surname) values (2,'2222@gmail.com','Second','2USER');
INSERT into comment (text) values ('Comment of second user');
INSERT into user_roles values (2,3);


--Third user/Manager
INSERT into users (login, password) values ('3','3333');
INSERT into account (user_id,email, name, surname) values (3,'3333@gmail.com','Third','3USER');
INSERT into comment (text) values ('Comment of third user');
INSERT into user_roles values (3,2);


--Fourth user
INSERT into users (login, password) values ('4','4444');
INSERT into account (user_id,email, name, surname) values (4,'4444@gmail.com','Fourth','4USER');
INSERT into comment (text) values ('Comment of fourth user');
INSERT into user_roles values (4,3);


--Fifth user/Manager
INSERT into users (id,login, password) values (5,'5','5555');
INSERT into account (user_id,email, name, surname) values (5,'5555@gmail.com','Fifth','5USER');
INSERT into comment (text) values ('Comment of fifth user');
INSERT into user_roles values (5,2);


--Sixth user
INSERT into users (id,login, password) values (6,'6','6666');
INSERT into account (user_id, email, name, surname) values (6,'6666@gmail.com','Sixth','6USER');
INSERT into comment (text) values ('Comment of sixth user');
INSERT into user_roles values (6,3);


--Seventh user/manager
INSERT into users (id,login, password) values (7,'7','7777');
INSERT into account (user_id, email, name, surname) values (7,'7777@gmail.com','Seventh','7USER');
INSERT into comment (text) values ('Comment of seventh user');
INSERT into user_roles values (7,2);


--Eighth user
INSERT into users (id,login, password) values (8,'8','8888');
INSERT into account (user_id,email, name, surname) values (8,'8888@gmail.com','Eighth','8USER');
INSERT into comment (text) values ('Comment of eighth user');
INSERT into user_roles values (8,3);

--Project number 1
INSERT into team (number_of_persons) values (2);
INSERT into project (link_to_git, name, progress, team_id) values ('https://github.com/TimofeyKononovich/currency_exchange.git','currency_exchange',100,1);
INSERT into user_team values (1,1);
INSERT into user_team values (1,2);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('2014-11-25 03:00:00','Exchange Application1','2015-11-25 04:00:00','Ready','Application1',1);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('2013-10-27 02:00:00','Exchange Application2','2014-11-25 05:00:00','Stopped','Application1',1);
INSERT into task_user (task_id, user_id) VALUES (1,1);
INSERT into task_user (task_id, user_id) VALUES (2,2);


--Project number 2
INSERT into team (number_of_persons) values (4);
INSERT into project (link_to_git, name, progress, team_id) values ('https://github.com/TimofeyKononovich/lab_9.git','Lab_9',70,2);
INSERT into user_team values (2,3);
INSERT into user_team values (2,4);
INSERT into user_team values (2,6);
INSERT into user_team values (2,8);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('1970-02-01 08:30:10','Lab_09','2003-05-19 20:04:00','In development','Application2',2);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('1970-03-01 23:01:00','Lab_09','1989-04-01 20:04:00','In development','Application2',2);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('1970-01-01  23:09:00','Lab_09','1999-02-23 09:00:00','Ready','Application2',2);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('1970-04-01 12:01:00','Lab_09','2000-01-24 12:00:08','Stopped','Application2',2);
INSERT into task_user (task_id, user_id) VALUES (3,3);
INSERT into task_user (task_id, user_id) VALUES (4,4);
INSERT into task_user (task_id, user_id) VALUES (5,6);
INSERT into task_user (task_id, user_id) VALUES (6,8);


--Project number 3
INSERT into team (number_of_persons) values (2);
INSERT into project (link_to_git, name, progress, team_id) values ('https://github.com/TimofeyKononovich/lab_8.git','Lab_8',24,3);
INSERT into user_team values (3,7);
INSERT into user_team values (3,8);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('2001-10-04 20:12:33','Lab_08','2002-01-01 00:00:00','Stopped','Application3',3);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('2001-08-04 13:32:09','Lab_08','2003-03-26 00:00:00','Ready','Application3',3);
INSERT into task_user (task_id, user_id) VALUES (7,7);
INSERT into task_user (task_id, user_id) VALUES (8,8);


--Project number 4
INSERT into team (number_of_persons) values (1);
INSERT into project (link_to_git, name, progress, team_id) values ('https://github.com/TimofeyKononovich/lab_5.git','lab_5',0,4);
INSERT into user_team values (4,5);
INSERT into task (date_time, description, spent_time, status, title, project_id) values ('1999-06-25 00:00:00','Lab_05','2001-03-05 00:00:00','Started','Application1',4);
INSERT into task_user (task_id, user_id) VALUES (9,5);




SELECT * from users;
SELECT * from account;
SELECT * from task;