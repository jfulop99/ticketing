package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticketing.partner.Partner;
import ticketing.ticketgroup.TicketGroup;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long id;

    private LocalDate dateOfNotification;

    private LocalDate dateOfCompletion;

    private Partner partner;

    private String description;

    private TicketGroup ticketGroup;

    private String descriptionOfSolution;

    private int workHours;

    private String reportId;

}
