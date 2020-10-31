-- расширение для постгрес
create extension if not exists pgcrypto;

update sweater.public.usr set password = crypt(password, gen_salt('bf', 8));