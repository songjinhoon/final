--user table
create table user1(
userId varchar(20) PRIMARY KEY,
userPasswd varchar(20) not null,
userName varchar(20) not null,
userEmail varchar(30) not null,
userEmailHash varchar(80) not null,
userEmailCheck number default 0,
userphone varchar(15) not null,
useraddress varchar(60) not null,
userscore number not null
);

select * from USER1;

update user1 set userEmailCheck=0 where userId='qwe';

