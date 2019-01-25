insert into authority (authority) values ('ROLE_USER');
insert into authority (authority) values ('ROLE_MANAGER');
insert into authority (authority) values ('ROLE_ADMIN');

insert into app_user (id, email, enabled, full_name, password, username)
values (nextval('app_user_id_seq'), 'danielthestranger@gmail.com', true, 'Daniel Stranger', '$2a$10$clAeXcZq.JcOrJadbH7T8OEh.COmxJS.AM3p4RROAsjPSyakrrnAC', 'dani');

insert into app_user_authority (id, authority) values (currval('app_user_id_seq'), 'ROLE_USER');
