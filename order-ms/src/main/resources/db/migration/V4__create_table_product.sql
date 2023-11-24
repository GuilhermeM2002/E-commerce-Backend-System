create table product_cart(
    id bigint not null,
    name varchar(80) not null,
    price numeric(10,2) not null,

    primary key(id)
);