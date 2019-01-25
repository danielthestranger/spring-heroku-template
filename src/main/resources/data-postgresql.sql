insert into authority (authority) values ('ROLE_USER');
insert into authority (authority) values ('ROLE_MANAGER');
insert into authority (authority) values ('ROLE_ADMIN');

insert into app_user (id, email, enabled, full_name, password, username)
values (nextval('app_user_id_seq'), 'danielthestranger@gmail.com', true, 'Daniel Stranger', '$2a$10$clAeXcZq.JcOrJadbH7T8OEh.COmxJS.AM3p4RROAsjPSyakrrnAC', 'dani');

insert into app_user_authority (id, authority) values (currval('app_user_id_seq'), 'ROLE_USER');

insert into location (id, name) values (default, 'Green Fox');
insert into service_type (id, name) values (default, 'Pizza time');
insert into atari_provider (id, description) values (default, 'A kapanyányimonyók');

insert into atari_calendar (id, comment, atari_provider_id, location_id, service_type_id) values (default, 'No comment', currval('atari_provider_id_seq'), currval('location_id_seq'), currval('service_type_id_seq'));

insert into time_slot (id, begin_time, booked, comments, end_time, atari_calendar_id, booked_by_id) values (default, '2019-01-01 12:00:00', false, 'Past', '2019-01-01 12:30:00', currval('atari_calendar_id_seq'), currval('app_user_id_seq'));

insert into time_slot (id, begin_time, booked, comments, end_time, atari_calendar_id, booked_by_id) values (default, '2019-02-01 12:00:00', false, 'Future free', '2019-02-01 12:30:00', currval('atari_calendar_id_seq'), currval('app_user_id_seq'));

insert into time_slot (id, begin_time, booked, comments, end_time, atari_calendar_id, booked_by_id) values (default, '2019-02-01 12:30:00', true, 'Future booked', '2019-02-01 13:00:00', currval('atari_calendar_id_seq'), currval('app_user_id_seq'));