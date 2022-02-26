create table body(
     id serial primary key,
     name varchar(255)
 );

create table engine(
     id serial primary key,
     name varchar(255)
 );

create table transmission(
     id serial primary key,
     name varchar(255)
 );

create table car(
     id serial primary key,
     name varchar(255),
     body_id int references body(id),
     engine_id int references engine(id),
     tm_id int references transmission(id)
 );


insert into body(name) values ('retro'), ('oldsmobile'), ('newage'), ('futuro'), ('fusion');
insert into engine(name) values ('gasoline'), ('diesel'), ('atomic'), ('antigravity'), ('sunlight');
insert into transmission(name) values ('manual'), ('automatic'), ('robotic'), ('sub - neural'), ('variator');

insert into car(name, body_id, engine_id, tm_id) values ('Родстер 3000', 1, 2, 1);
insert into car(name, body_id, engine_id, tm_id) values ('ГАЗ Байкал', 2, 4, 4);
insert into car(name, body_id, engine_id, tm_id) values ('БелАЗ Малыш', 4, 3, 3);
insert into car(name, body_id, engine_id, tm_id) values ('ЗаЗ Оболтус-17', 3, 4, 2);
insert into car(name, body_id, engine_id, tm_id) values ('УАЗ Space', 1, 4, 1);
insert into car(name, body_id, engine_id, tm_id) values ('Лада Стрела', 4, 1, 4);
insert into car(name, body_id, engine_id, tm_id) values ('Taxi', 3, 1, 2);
insert into car(name, body_id, engine_id, tm_id) values ('ГАЗ-69 new', 2, 2, 4);

select car.name, body.name, eng.name, tm.name
from car
left join body on car.body_id = body.id
left join engine eng on car.engine_id = eng.id
left join transmission tm on car.tm_id = tm.id
where car.name is not null;

select body.name as кузов
from car
right join body on car.body_id = body.id
where car.name is null;

select engine.name as двигатель
from car
right join engine on car.engine_id = engine.id
where car.name is null;

select transmission.name as кузов
from car
right join transmission on car.tm_id = transmission.id
where car.name is null;
