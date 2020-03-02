--user table
create table user(
userid varchar(20),
userpassword varchar(20),
username varchar(20),
userEmail varchar(30),
userEmailHash varchar(50),
userEmailCheck int default 0,
userphone varchar(15),
useraddress varchar(60),
userscore int
);

select * from user;