--user table
create table user(
userId varchar(20) p,
userPasswd varchar(20),
userName varchar(20),
userEmail varchar(30),
userEmailHash varchar(80),
userEmailCheck int default 0,
userphone varchar(15),
useraddress varchar(60),
userscore number
);

SELECT userId 
		FROM user 
		WHERE userId = 'dbfla123';

select * from user;

delete from user;