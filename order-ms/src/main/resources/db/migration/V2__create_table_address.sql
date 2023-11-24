create table address(
    id bigint not null auto_increment,
    cep varchar(9) not null,
    state varchar(40) not null,
    city varchar(60) not null,
    neighborhood varchar(40) not null,
    street varchar(40) not null,
    number varchar(10) not null,

    primary key(id)
);