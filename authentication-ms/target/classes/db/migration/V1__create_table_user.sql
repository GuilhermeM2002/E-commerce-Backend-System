create table t_user(
    id bigint not null auto_increment,
    role_fk bigint not null,
    fullName varchar(60) not null,
    cpf varchar(11) not null,
    email varchar(60) not null,
    password varchar(20) not null,
    dateBirthday date not null,
    accountNotExpired boolean not null,
    credentialsNonExpired boolean not null,
    enabled boolean not null,

    primary key(id)
);