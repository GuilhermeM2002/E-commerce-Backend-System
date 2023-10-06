create table productCart(
    id bigint not null auto_increment,
    name varchar(80) not null,
    price numeric(10,2) not null,
    quantity integer not null,

    primary key(id)
);