--user table
create table user1(
userid varchar(20) PRIMARY KEY,
userpasswd varchar(20) not null,
username varchar(20) not null,
useremail varchar(30) not null,
useremailhash varchar(80) not null,
useremailcheck number default 0,
userphone varchar(15) not null,
useraddress varchar(60) not null,
userscore number not null
);

drop table user1;

update user1 set userscore=60;

select * from user1;

update user1 set userEmailCheck=0 where userId='qwe';

--userScore Á¶È¸
SELECT userScore
		FROM user1 
		WHERE userId = 'dbfla132';
		
				SELECT useremailcheck FROM user1
		WHERE userid = 'dbfla132'