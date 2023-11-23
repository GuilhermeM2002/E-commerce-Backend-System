create table orders(
    id bigint not null auto_increment,
    address_fk bigint not null,
    dt_order datetime not null,
    vl_order numeric(9,2) not null,
    tracking_code varchar(100) not null,
    status varchar(30) not null,

    primary key (id)
);