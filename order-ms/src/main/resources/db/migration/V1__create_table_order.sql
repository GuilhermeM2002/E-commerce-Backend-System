create table orders(
    id bigint not null auto_increment,
    dt_order datetime not null,
    vl_order numeric(9,2) not null,
    trackingCode varchar(50) not null,
    status varchar(30) not null,

    primary key (id)
);