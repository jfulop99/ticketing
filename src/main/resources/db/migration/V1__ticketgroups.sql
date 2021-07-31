create table ticket_groups (
    id  bigint not null auto_increment,
    ticket_group varchar(25) not null,
    primary key (id)
                           );

insert into ticket_groups (ticket_group) values ('TV');
insert into ticket_groups (ticket_group) values ('LCD');
insert into ticket_groups (ticket_group) values ('Antenna');
insert into ticket_groups (ticket_group) values ('AV technika');
insert into ticket_groups (ticket_group) values ('Kábel');
insert into ticket_groups (ticket_group) values ('Rendszer');
insert into ticket_groups (ticket_group) values ('Számítógép');
insert into ticket_groups (ticket_group) values ('Távirányító');
insert into ticket_groups (ticket_group) values ('Fejállomás');
insert into ticket_groups (ticket_group) values ('Network');
