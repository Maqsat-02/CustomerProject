drop table if exists customer;

create table customer(
    id varchar(20) NOT NULL,
    fullName varchar(100),
    docNum varchar(100),
    docDetails varchar(100),
    birthDate DATE,
    resident varchar(20)
);