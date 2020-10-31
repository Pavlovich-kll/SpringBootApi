insert into sweater.public.usr (id, username, password, active)
values (1, 'admin', '123', true);

insert into sweater.public.user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');