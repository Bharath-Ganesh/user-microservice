create schema bookonline;
use bookonline;
drop table  user;

create table user(
id integer NOT NULL AUTO_INCREMENT primary key,
username varchar(50),
email_id varchar(50),
address varchar(50),
password varchar(50));



insert into user values(1,'bharath','bharath.ganesh@gmail.com','Sreenandanam','1234');

select * from user;
drop table user;