create table shopping_cart(
    id bigint not null auto_increment,
    token varchar(50),
    user_email varchar(70) unique,
    date_creation datetime not null,
    status varchar(15) not null,

    primary key (id)
)