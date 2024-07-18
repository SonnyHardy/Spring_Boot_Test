create table if not exists customers(
    id integer primary key auto_increment ,
    email varchar(100) not null
);

delete from customers;

insert into customers(email) values ('sonnyhardy@gmail.com');
insert into customers(email) values ('sonny@gmail.com');
insert into customers(email) values ('paul@gmail.com');
insert into customers(email) values ('marie@gmail.com');
insert into customers(email) values ('jean@gmail.com');
insert into customers(email) values ('rayan@gmail.com');