insert into user (id, name, birth_date) values(10001, 'AB', sysdate());
insert into user (id, name, birth_date) values(10002, 'Jill', sysdate());
insert into user (id, name, birth_date) values(10003, 'Jam', sysdate());

insert into post (id, description, user_id) values (11001, 'my first post', 10001);
insert into post (id, description, user_id) values (11002, 'my second post', 10001);