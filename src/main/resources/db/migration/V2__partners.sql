create table partners (
                               id  bigint not null auto_increment,
                               name varchar(255) not null,
                               accounting_id varchar(20),
                               postal_code varchar(10),
                               city varchar(255),
                               address_line1 varchar(255),
                               address_line2 varchar(255),
                               primary key (id)
);

insert into ticket_groups (ticket_group) values ('TV');
