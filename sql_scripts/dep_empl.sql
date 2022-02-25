create table departments(
     id serial primary key,
     name varchar(255)
 );

create table emploers(
     id serial primary key,
     name varchar(255),
     departments_id int references departments(id)
 );

insert into departments(name) values ('police'), ('fire service'), ('emergency'), ('postal service'), ('sewer service');

insert into emploers(name, departments_id) values ('Boromir', 1);
insert into emploers(name, departments_id) values ('Faramir', 1);

insert into emploers(name, departments_id) values ('Gendalf', 2);

insert into emploers(name, departments_id) values ('Arven', 3);
insert into emploers(name, departments_id) values ('Galadriel', 3);
insert into emploers(name, departments_id) values ('Elrond', 3);

insert into emploers(name, departments_id) values ('Frodo', 4);
insert into emploers(name, departments_id) values ('Sam', 4);

select * from departments d left join emploers e on d.id = e.departments_id;

select * from departments d right join emploers e on d.id = e.departments_id;

select * from departments d full join emploers e on d.id = e.departments_id;

select * from departments d cross join emploers e;

select * from departments d left join emploers e on d.id = e.departments_id where departments_id is null;

select * from departments d left join emploers e on d.id = e.departments_id;
select d.id, d.name, e.id, e.name, e.departments_id from emploers e right join departments d on d.id = e.departments_id;

create table teens(
     id serial primary key,
     name varchar(255),
	 gender varchar(255)
 );

insert into teens(name, gender) values ('Ипполит', 'М'), ('Сигизмунд', 'М'), ('Полуэкт', 'М'), ('Эвридика', 'Ж'), ('Офелия', 'Ж'), ('Изольда', 'Ж');

select * from teens as t1 cross join teens as t2
where t1.gender not like t2.gender;