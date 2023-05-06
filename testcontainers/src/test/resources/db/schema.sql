create table human
(
    id       bigint  not null auto_increment,
    age      integer ,
    username varchar(255),
    primary key (id)
);

insert into human (age, username)
values (20, "def");
