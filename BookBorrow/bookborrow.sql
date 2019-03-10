-- use bookborrow;
-- drop table if exists `reader`;
-- drop table if exists `readertype`;
-- drop table if exists `user`;

-- 读者类型表
create table if not exists `readertype`(
	`id` int not null auto_increment,
    `typename` varchar(20),
    `maxborrownum` int ,
	`limit` int ,
    primary key(`id`)
);
-- 读者表
create table if not exists `reader`(
	`readerid` char(8) not null,
    `type` int ,
	`name` varchar(20),
    `age` int ,
    `sex` char(4) ,
    `phone` char(11),
    `dept` varchar(20),
    `regdate` date,
    primary key(`readerid`),
    foreign key(`type`) references readertype(`id`)
);
-- 用户表
create table if not exists `user`(
	`id` int not null auto_increment,
    `username` varchar(20),
    `password` varchar(20),
    primary key(`id`)  
);
-- 图书类型表
create table if not exists `booktype`(
	`id` int not null auto_increment,
    `typename` varchar(30) ,
    primary key(id)
); 
-- 图书信息表
create table if not exists `book`(
	`ISBN` char(10)  not null,
    `typeid` int ,
    `bookname` varchar(30),
    `author` varchar(30),
    `publish` varchar(30),
    `publishdate` date,
    `publishnum` int,
    `unitprice` double,
    primary key(`ISBN`),
    foreign key(`typeid`) references booktype(`id`)
);
-- 借阅表
create table if not exists `borrowbook`(
	`id` bigint  not null auto_increment,
	`readerid` char(8)  not null,
    `ISBN` char(10) not  null ,
    `borrowdate` date ,
    `returndate` date,
    `fine` double,
    primary key(`id`) ,
    foreign key(`readerid`) references reader(`readerid`),
    foreign key(`ISBN`) references book(`ISBN`)
);


-- use bookborrow;
-- insert into user(username,password) values("kirito","kirito");
-- select * from user  

