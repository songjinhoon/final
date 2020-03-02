--user table
create table user(
userId varchar(20),
userPasswd varchar(20),
userName varchar(20),
userEmail varchar(30),
userEmailHash varchar(80),
userEmailCheck int default 0,
userphone varchar(15),
useraddress varchar(60),
userscore int
);

select * from user;

delete from user;