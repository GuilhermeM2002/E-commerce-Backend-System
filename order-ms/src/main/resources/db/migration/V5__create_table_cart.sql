create table cart(
    id bigint not null,
    user_email varchar(70) unique,

    primary key (id)
)