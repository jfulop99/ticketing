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
import ticketing.partner.Address;
import ticketing.partner.CreatePartnerCommand;
import ticketing.partner.PartnerDto;
import ticketing.partner.PartnerService;
import ticketing.ticketgroup.CreateTicketGroupCommand;
import ticketing.ticketgroup.TicketGroupDto;
import ticketing.ticketgroup.TicketGroupService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from materials","delete from tickets",  "delete from ticket_groups", "delete from partners"})
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
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
            .postForObject("/api/tickets/",
                new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId(), "Not working",
                        ticketGroup.getId(), null, 0, null),
                TicketDto.class);
        assertEquals("Not working", ticket.getDescription());


    }

    @Test
    void findAllTicketsTest() {
        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId(), "Not working",
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

        assertThat(tickets).extracting(TicketDto::getId).containsExactly(ticket.getId());

    }

    @Test
    void updateTicketTest(){

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        template.put(String.format("/api/tickets/%d", id), new UpdateTicketCommand( new FulfillmentPeriod(LocalDate.now(), null), "Not Working too", null, 0, null));

        ticket = template
                .getForObject(String.format("/api/tickets/%d", id), TicketDto.class);

        assertEquals("Not Working too", ticket.getDescription());

    }

    @Test
    void deleteTicketTest(){

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        template.delete(String.format("/api/tickets/%d", id));

        Problem problem = template.getForObject(String.format("/api/tickets/%d", id), Problem.class);
        assertEquals("500 Internal Server Error", Objects.requireNonNull(problem.getStatus()).toString());
        assertEquals("Cannot find ticket", problem.getDetail());

    }

    @Test
    void addMaterialTest(){
        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        TicketDto anotherTicket = template
                .postForObject("/api/tickets/" + id + "/materials",
                        new CreateMaterialCommand("55HFL6014", 250000), TicketDto.class);

        assertEquals("55HFL6014", anotherTicket.getMaterials().get(0).getName());
    }

    @Test
    void createTicketWithWrongDateTest() {

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        Problem problem = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), LocalDate.of(2021,1,1)), partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        Problem.class);

        assertEquals(400, Objects.requireNonNull(problem.getStatus()).getStatusCode());

        problem = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(null, null), partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        Problem.class);

        assertEquals(400, Objects.requireNonNull(problem.getStatus()).getStatusCode());

    }

    @Test
    void createTicketWithWrongPartner() {

        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        Problem problem = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId()-1, "Not working",
                                ticketGroup.getId(), null, 0, null),
                        Problem.class);

        assertEquals(400, Objects.requireNonNull(problem.getStatus()).getStatusCode());
        List<Map<String, String >> violations = (List<Map<String, String>>) problem.getParameters().get("violations");
        assertEquals("Invalid partner id", violations.get(0).get("message"));

    }

    @Test
    void deleteAllMaterialsByTicketId(){
        PartnerDto partner = partnerService.createPartner(new CreatePartnerCommand("Hotel Intercontinental", "0025",
                new Address("H-1051", "Budapest", "Széchenyi tér 1.", null)));

        TicketGroupDto ticketGroup = ticketGroupService.createGroup(new CreateTicketGroupCommand("HeadEnd"));

        TicketDto ticket = template
                .postForObject("/api/tickets/",
                        new CreateTicketCommand(new FulfillmentPeriod(LocalDate.now(), null), partner.getId(), "Not working",
                                ticketGroup.getId(), null, 0, null),
                        TicketDto.class);

        Long id = ticket.getId();

        TicketDto anotherTicket = template
                .postForObject("/api/tickets/" + id + "/materials",
                        new CreateMaterialCommand("55HFL6014", 250000), TicketDto.class);

        assertThat(anotherTicket.getMaterials()).hasSize(1);

        template.delete(String.format("/api/tickets/%d/materials", id));

        anotherTicket = template
                .getForObject(String.format("/api/tickets/%d", id), TicketDto.class);

        assertThat(anotherTicket.getMaterials()).isEmpty();


    }

}