insert into role(name) values ('detective');
insert into role(name) values ('doctor');

insert into users(name, id_role) values ('Holmes', 1);
insert into users(name, id_role) values ('Watson', 2);

insert into rules(name) values ('investigation');
insert into rules(name) values ('heal');
insert into rules(name) values ('chemical');

insert into role_rules(id_role, id_rules) values (1, 1);
insert into role_rules(id_role, id_rules) values (1, 3);
insert into role_rules(id_role, id_rules) values (2, 2);
insert into role_rules(id_role, id_rules) values (2, 3);

insert into category(name) values ('not urgent');
insert into category(name) values ('urgently');

insert into state(name) values ('waitnig');
insert into state(name) values ('in progress');
insert into state(name) values ('complete');

insert into item(name, id_users, id_category, id_state) values ('catch dog', 1, 2, 3);
insert into item(name, id_users, id_category, id_state) values ('heal sir Henry', 2, 1, 2);

insert into comments(name, id_item) values ('use oatmeal porridge', 2);

insert into attachs(name, id_item) values ('evidence', 1);





