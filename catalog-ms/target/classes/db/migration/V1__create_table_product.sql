create table product(
    code_product bigint not null auto_increment,
    name varchar(40) not null,
    price numeric(10,2) not null,
    description varchar(255) not null,
    category varchar(40) not null,

    primary key(code_product)
)