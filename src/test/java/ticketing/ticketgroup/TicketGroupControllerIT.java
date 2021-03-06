package ticketing.ticketgroup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from materials","delete  from tickets",  "delete from ticket_groups"})
class TicketGroupControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void createGroup() {
        TicketGroupDto group = template
                .postForObject("/api/groups",
                        new CreateTicketGroupCommand("HeadEnd"),
                        TicketGroupDto.class);
        assertEquals("HeadEnd", group.getGroup());

    }

    @Test
    void getGroups() {
        TicketGroupDto group = template
                .postForObject("/api/groups",
                        new CreateTicketGroupCommand("HeadEnd"),
                        TicketGroupDto.class);


        List<TicketGroupDto> groups = template.exchange
                ("/api/groups",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TicketGroupDto>>() {
                        }).getBody();

        assertThat(groups).hasSize(1)
                .extracting(TicketGroupDto::getGroup)
                .containsExactly("HeadEnd");
    }

    @Test
    void updateGroupById() {
        TicketGroupDto group = template
                .postForObject("/api/groups",
                        new CreateTicketGroupCommand("HeadEnd"),
                        TicketGroupDto.class);

        Long id = group.getId();

        template.put(String.format("/api/groups/%d", id), new UpdateGroupCommand("Fej??llom??s"));

        group = template
                .getForObject(String.format("/api/groups/%d", id), TicketGroupDto.class);

        assertEquals("Fej??llom??s", group.getGroup());
    }

    @Test
    void deleteGroupById() {
        TicketGroupDto group = template
                .postForObject("/api/groups",
                        new CreateTicketGroupCommand("HeadEnd"),
                        TicketGroupDto.class);

        Long id = group.getId();

        template.delete(String.format("/api/groups/%d", id));

        Problem problem = template.getForObject(String.format("/api/groups/%d", id), Problem.class);
        assertEquals("500 Internal Server Error", problem.getStatus().toString());
        assertEquals(String.format("Cannot find group id = %d", id), problem.getDetail());
    }

    @Test
    void createGroupNotValidTest() {
        Problem problem = template
                .postForObject("/api/groups",
                        new CreateTicketGroupCommand("T"),
                        Problem.class);
        assertEquals(Status.BAD_REQUEST, problem.getStatus());

    }

    @Test
    void updateGroupNotValidTest() {
        TicketGroupDto group = template
                .postForObject("/api/groups",
                        new CreateTicketGroupCommand("HeadEnd"),
                        TicketGroupDto.class);

        Long id = group.getId();

        Problem problem = template
                .exchange(String.format("/api/groups/%d", id),
                        HttpMethod.PUT,
                        new HttpEntity<>(new UpdateGroupCommand("T")),
                        Problem.class).getBody();
        assert problem != null;
        assertEquals(Status.BAD_REQUEST, problem.getStatus());

    }



}