create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('ant', 1280, date '1810-01-02');
insert into fauna(name, avg_age, discovery_date) values ('fish', 7300, date '1805-02-04');
insert into fauna(name, avg_age, discovery_date) values ('horse', 10000, date '1800-03-06');
insert into fauna(name, avg_age, discovery_date) values ('shrimp', 1600, date '1855-04-08');
insert into fauna(name, avg_age, discovery_date) values ('butеerfly', 20, date '1845-05-10');
insert into fauna(name, avg_age, discovery_date) values ('silkworm', 3, date '1835-06-12');
insert into fauna(name, avg_age, discovery_date) values ('turtle', 100000, date '1825-07-14');
insert into fauna(name, avg_age, discovery_date) values ('parrot', 36000, date '1815-08-16');
insert into fauna(name, avg_age, discovery_date) values ('elephant', 25000, date '1865-09-18');
insert into fauna(name, avg_age, discovery_date) values ('cat', 5500, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age >= 10000 and avg_age <= 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date <= '01.01.1950';