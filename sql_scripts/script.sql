create table char (
    id serial primary key,
    name varchar(255),
    melee_wearpon text,
    char_strength int
);

select * from char;

insert into char(name, melee_wearpon, char_strength) values ('Groo', 'sword', 12);

select * from char;

update char set melee_wearpon = 'two swords';

select * from char;

delete from char;

select * from char;
