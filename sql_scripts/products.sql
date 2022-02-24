create table type(
     id serial primary key,
     name varchar(255)
 );

create table product(
     id serial primary key,
     name varchar(255),
     type_id int references type(id),
     expired_date date,
     price float
 );

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('ДЕСЕРТ'), ('КРУПА'), ('ОВОЩЬ'), ('ФРУКТ'), ('МЯСО');

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, date '2022-03-10', 112.9);
insert into product(name, type_id, expired_date, price) values ('Сыр российский', 1, date '2022-03-24', 539.0);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date '2022-03-15', 233.9);
insert into product(name, type_id, expired_date, price) values ('Сыр пармезан', 1, date '2022-05-10', 1124.9);

insert into product(name, type_id, expired_date, price) values ('Молоко коровье', 2, date '2022-02-28', 63.4);
insert into product(name, type_id, expired_date, price) values ('Молоко козье', 2, date '2022-02-22', 109.2);

insert into product(name, type_id, expired_date, price) values ('Мороженое эскимо', 3, date '2022-03-25', 75.0);
insert into product(name, type_id, expired_date, price) values ('Мороженое стаканчик', 3, date '2022-02-23', 65.0);
insert into product(name, type_id, expired_date, price) values ('Шоколадная паста', 3, date '2022-02-24', 323.0);
insert into product(name, type_id, expired_date, price) values ('Печенье', 3, date '2022-08-24', 56.9);

insert into product(name, type_id, expired_date, price) values ('Рисовая курпа', 4, date '2023-02-24', 131.0);
insert into product(name, type_id, expired_date, price) values ('Гречневая курпа', 4, date '2023-02-24', 127.0);
insert into product(name, type_id, expired_date, price) values ('Манная крупа', 4, date '2022-10-24', 99.9);

insert into product(name, type_id, expired_date, price) values ('Картофель', 5, date '2022-05-24', 45.0);
insert into product(name, type_id, expired_date, price) values ('Морковь', 5, date '2022-06-24', 37.0);
insert into product(name, type_id, expired_date, price) values ('Лук', 5, date '2022-01-24', 35.0);

insert into product(name, type_id, expired_date, price) values ('Яблоки', 6, date '2022-05-15', 105.0);
insert into product(name, type_id, expired_date, price) values ('Груши', 6, date '2022-04-24', 134.0);
insert into product(name, type_id, expired_date, price) values ('Апельсины', 6, date '2022-04-24', 96.0);

insert into product(name, type_id, expired_date, price) values ('Курица', 7, date '2022-03-01', 265.0);
insert into product(name, type_id, expired_date, price) values ('Говядина', 7, date '2022-03-02', 639.9);

select * from product
join type on product.type_id = type.id
where type.name like 'СЫР';

select * from product where name like '%Мороженое%';

select * from product where expired_date < current_date;

select * from product where price in (select max(price) from product);

select type.name as имя_типа, count(distinct p.name) as количество
from product as p
join type
on p.type_id = type.id
group by type.name;

select * from product
join type on product.type_id = type.id
where type.name like 'СЫР' or type.name like 'МОЛОКО';

select type.name as имя_типа, count(distinct p.name) as количество
from product as p
join type
on p.type_id = type.id
group by type.name
having count(distinct p.name) < 3;

select p.name, t.name
from product as p
join type as t
on p.type_id = t.id;
