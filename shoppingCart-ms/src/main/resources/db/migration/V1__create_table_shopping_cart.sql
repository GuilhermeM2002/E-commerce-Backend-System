create table shopping_cart(
    id bigint not null,
    token varchar(50) not null,
    date_creation datetime not null,
    status varchar(15) not null,

    primary key (id)
)