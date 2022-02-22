create table character(
     id serial primary key,
     name varchar(255)
 );

 create table attribute(
     id serial primary key,
     name varchar(255),
     value int,
     character_id int references character(id)
 );

select * from character;

select * from attribute;

--create table character(
--     id serial primary key,
--     name varchar(255)
-- );

 create table enemy(
     id serial primary key,
     name varchar(255)
 );

 create table character_enemy(
     id serial primary key,
     character_id int references character(id),
     enemy_id int references enemy(id)
 );

--create table character(
--     id serial primary key,
--     name varchar(255)
-- );

 create table quenta(
     id serial primary key,
     name varchar(255)
 );

 create table character_quenta(
     id serial primary key,
     character_id int references character(id) unique,
     quenta_id int references quenta(id) unique
 );