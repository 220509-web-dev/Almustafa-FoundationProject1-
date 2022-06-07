--establish a 'namespace' for related DB entities to exist within
create schema football_app;

--convenience command that will help us to reference the schema above
set search_path to football_app;


create table app_users (
    id          int generated always as identity,
    username    varchar unique not null check (length(username) >=4),
    password    varchar not null check (length(password) >=8)
);
