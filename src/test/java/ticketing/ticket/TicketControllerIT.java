package ticketing.ticket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import ticketing.material.CreateMaterialCommand;
import ticketing.partner.*;
import ticketing.ticketgroup.TicketGroupDto;
import ticketing.ticketgroup.TicketGroupService;
import ticketing.ticketgroup.CreateTicketGroupCommand;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from tickets",  "delete from ticket_groups", "delete from partners"})
class TicketControllerIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    PartnerService partnerService;

    @Autowired
    TicketGroupService ticketGroupService;

    @Test
    void createTicketTest() {

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
            .postForObject("/api/tickets/",
                new CreateTicketCommand(LocalDate.now(), null, partner.getId(), "Not working",
                        ticketGroup.getId(), null, 0, null),
                TicketDto.class);
        assertEquals("Not working", ticket.getDescription());


    }

    @Test
    void findAllTicketsTest() {
        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(LocalDate.now(), null, partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        List<TicketDto> tickets = template.exchange
                ("/api/tickets",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TicketDto>>() {
                        }).getBody();

        assertThat(tickets).hasSize(1)
                .extracting(TicketDto::getDescription)
                .containsExactly("Not working");

    }

    @Test
    void updateTicketTest(){

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(LocalDate.now(), null, partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        template.put(String.format("/api/tickets/%d", id), new UpdateTicketCommand(null, "Not Working too", null, 0, null));

        ticket = template
                .getForObject(String.format("/api/tickets/%d", id), TicketDto.class);

        assertEquals("Not Working too", ticket.getDescription());

    }

    @Test
    void deleteTicketTest(){

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(LocalDate.now(), null, partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        template.delete(String.format("/api/tickets/%d", id));

        Problem problem = template.getForObject(String.format("/api/tickets/%d", id), Problem.class);
        assertEquals("500 Internal Server Error", problem.getStatus().toString());
        assertEquals("Cannot find ticket", problem.getDetail());

    }

    @Test
    void addMaterialTest(){
        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(LocalDate.now(), null, partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        TicketDto anotherTicket = template
                .postForObject("/api/tickets/" + id + "/materials",
                        new CreateMaterialCommand("55HFL6014", 250000), TicketDto.class);

        assertEquals("55HFL6014", anotherTicket.getMaterials().get(0).getName());
    }
}