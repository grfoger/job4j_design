CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'Изенгард'), (2, 'Шир'), (3, 'Рохан'), (4, 'Гондор'), (5, 'Мордор');
insert into person(id, name, company_id) values (11, 'Саруман', 1), (12, 'Гримма', 1), (13, 'Урук-хай', 1);
insert into person(id, name, company_id) values (21, 'Фродо', 2), (22, 'Сэм', 2), (23, 'Мерри', 2), (24, 'Пиппен', 2);
insert into person(id, name, company_id) values (31, 'Теоден', 3), (32, 'Иовин', 3), (33, 'Эомер', 3);
insert into person(id, name, company_id) values (41, 'Арагорн', 4), (42, 'Боромир', 4), (43, 'Фарамир', 4), (44, 'Гендальф', 4);
insert into person(id, name, company_id) values (51, 'Саурон', 5), (52, 'Назгул', 5), (53, 'Балрог', 5);
select * from company c full join person p on c.id = p.company_id;


select p.name, c.name
from person p
join company c
on c.id = p.company_id
where p.company_id != 5;

select c.name, count(distinct p.name)
from person as p
join company as c
on p.company_id = c.id
group by c.name
having count(distinct p.name) >= all (
select count(distinct p.name)from person as p
join company as c
on p.company_id = c.id
group by c.name);
