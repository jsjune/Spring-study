-- create table human (
--                        id bigint not null,
--                        birth date,
--                        money integer,
--                        name varchar(255),
--                        primary key (id)
-- );

create table human (
                       id bigint not null,
                       birth date,
                       money integer,
                       name varchar(255),
                       version integer,
                       primary key (id)
);


create table home (
                      id bigint not null,
                      address varchar(255),
                      name varchar(255),
                      price integer not null,
                      primary key (id)
);

insert into human
values (1, '1990-10-31', 10000, 'aaa',1);

insert into home
values ( 1, '서울', 'building', 30000 )
