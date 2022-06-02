set search_path to football_app;

insert into app_users (username, password)
values 
('Almustafa', 'password'),
('Moss', 'vikings3333'),
('Rice', 'niners3333'),
('Owens', 'cowboys3333');

-- Get user by username
select * from app_users where username = 'Almustafa';

-- Get user by password
select * from app_users where password = 'cowboys3333';