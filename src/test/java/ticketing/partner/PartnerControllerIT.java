package ticketing.partner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete  from tickets", "delete from partners" })
class PartnerControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void findAllPartnersTest() {

        template.postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Hotel Intercontinental", "0025",
                                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)),
                        PartnerDto.class);
        template.postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Four Seasons Budapest", "0026",
                                new Address("H-1051", "Budapest", "Szécheny tér 3.", null)),
                        PartnerDto.class);


        List<PartnerDto> partners = template.exchange
                ("/api/tickets/partners",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PartnerDto>>() {
                        }).getBody();

        assertThat(partners).hasSize(2)
                .extracting(PartnerDto::getAccountingId)
                .containsExactly("0025", "0026");

    }

    @Test
    void findPartnerByIdTest() {
        PartnerDto partner = template
                .postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Hotel Intercontinental", "0025",
                                new Address("H-1051", "Budapest", "Szécheny tér 1.", null)),
                        PartnerDto.class);
        Long id = partner.getId();

        partner = template
                .getForObject(String.format("/api/tickets/partners/%d", id), PartnerDto.class);


        assertEquals("Hotel Intercontinental", partner.getName());


    }

    @Test
    void createPartnerTest() {
        PartnerDto partner = template
                .postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Hotel Intercontinental", "0025", new Address("H-1051", "Budapest", "Szécheny tér 1.", null)),
                        PartnerDto.class);
        assertEquals("Hotel Intercontinental", partner.getName());

    }

    @Test
    void updatePartnerByIdTest() {
        PartnerDto partner = template
                .postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Hotel Intercontinental", "0025", new Address("H-1051", "Budapest", "Szécheny tér 1.", null)),
                        PartnerDto.class);
        Long id = partner.getId();

        template.put(String.format("/api/tickets/partners/%d", id),
                new UpdatePartnerCommand("Hotel Intercontinental Budapest", "0025", new Address("H-1051", "Budapest", "Szécheny tér 1.", null)));

        partner = template
                .getForObject(String.format("/api/tickets/partners/%d", id), PartnerDto.class);


        assertEquals("Hotel Intercontinental Budapest", partner.getName());
    }

    @Test
    void deletePartnerByIdTest() {
        PartnerDto partner = template
                .postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Hotel Intercontinental", "0025", new Address("H-1051", "Budapest", "Szécheny tér 1.", null)),
                        PartnerDto.class);
        Long id = partner.getId();

        template.delete(String.format("/api/tickets/partners/%d", id));

        Problem problem = template.getForObject(String.format("/api/tickets/partners/%d", id), Problem.class);
        assertEquals("500 Internal Server Error", problem.getStatus().toString());
        assertEquals(String.format("Cannot find partner id = %d", id), problem.getDetail());

    }

    @Test
    void createPartnerNotValidTest() {
        Problem problem = template
                .postForObject("/api/tickets/partners",
                        new CreatePartnerCommand("Hotel Intercontinental", "", new Address("H-1051", "Budapest", "Szécheny tér 1.", null)),
                        Problem.class);
        assertEquals(Status.BAD_REQUEST, problem.getStatus());

    }

}