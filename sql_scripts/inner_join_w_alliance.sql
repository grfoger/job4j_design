create table wearpon(
    id serial primary key,
    name varchar(255)
);

create table character(
    id serial primary key,
    name varchar(255),
    class varchar(255),
    wearpon_id int references wearpon(id)
);

insert into wearpon(name) values ('Stuff');
insert into wearpon(name) values ('Sword');
insert into wearpon(name) values ('Shovel');

insert into character(name, class, wearpon_id) values ('Gendalf', 'Mage', 1);
insert into character(name, class, wearpon_id) values ('Saruman', 'Warlock', 1);
insert into character(name, class, wearpon_id) values ('Groo', 'Warrior', 2);
insert into character(name, class, wearpon_id) values ('Grisha-Janitor', 'Paladin', 3);

select ch.name as Имя, ch.class as Класс, w.name as Оружие
from character as ch
join wearpon as w
on ch.wearpon_id = w.id;