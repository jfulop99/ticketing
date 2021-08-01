create table tickets (
                          id  bigint not null auto_increment,
                          date_of_notification date not null ,
                          date_of_completion date,
                          description varchar(255) not null ,
                          partner_id bigint not null ,
                          ticket_group_id bigint not null ,
                          desc_solution varchar(255),
                          work_hours int,
                          report_id varchar(20),
                          constraint FK_PARTNER foreign key (partner_id) references ticketing.partners (id)  ON UPDATE RESTRICT ON DELETE RESTRICT,
                          constraint FK_TICKET_GROUP foreign key (ticket_group_id) references ticketing.ticket_groups (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
                          primary key (id)
);
