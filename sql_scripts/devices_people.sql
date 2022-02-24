create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Конь', 82.5), ('Тачанка', 47.8), ('Пулемёт', 63.1), ('Папаха', 5.2), ('Шашка', 11.4), ('Наган', 16.7);
insert into people(name) values ('Чапай'), ('Петька'), ('Анка');
insert into devices_people(device_id, people_id) values (1, 1), (4, 1), (5, 1), (6, 1);
insert into devices_people(device_id, people_id) values (1, 2), (5, 2), (6, 2);
insert into devices_people(device_id, people_id) values (2, 3), (3, 3);

select avg(price) as Средняя_цена from devices;

select p.name as Имя, avg(d.price) as Цена_снаряжения
from people as p
join devices_people as dp on p.id = dp.people_id
join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 50;