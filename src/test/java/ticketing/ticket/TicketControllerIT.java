package ticketing.ticket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete  from tickets",  "delete from ticket_groups", "delete from partners"})
class TicketControllerIT {

    @Test
    void createTicket() {
    }

    @Test
    void findAllTickets() {
    }
}