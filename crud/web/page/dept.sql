drop database if exists dep_db;
create database dep_db;
create table dept (
    id int primary key auto_increment,
    dept_no int unique,
    dept_name varchar(10) not null,
    dept_loc varchar(25) not null
);
insert into dept(dept_no, dept_name, dept_loc)
values(10,'营销部','上海'),(20,'传媒部','江苏'),(30,'研发部','广州'),(40,'技术部','深圳');
