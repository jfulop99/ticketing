create table materials (
                     id bigint not null auto_increment,
                     name varchar(255) not null,
                     price int not null,
                     ticket_id bigint not null,
                     primary key (id)
);

alter table materials
                    add constraint FK_MATERIAL
                    foreign key (ticket_id)
                    references tickets (id);
