package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticketing.material.Material;
import ticketing.partner.Partner;
import ticketing.ticketgroup.TicketGroup;

import java.time.LocalDate;
import java.util.List;

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

    private List<Material> materials;

}
