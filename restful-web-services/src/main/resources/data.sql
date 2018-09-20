insert into user values(10001,sysdate(),'user1')
insert into user values(10002,sysdate(),'user2')
insert into user values(10003,sysdate(),'user3')

insert into post(id,description,user_id) values(11000,'My First POst',10001)
insert into post(id,description,user_id) values(11001,'My Second POst',10001)
insert into post(id,description,user_id) values(11002,'Another user POst',10002)