package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticketing.material.Material;
import ticketing.material.MaterialDto;
import ticketing.partner.Partner;
import ticketing.partner.PartnerDto;
import ticketing.ticketgroup.TicketGroup;
import ticketing.ticketgroup.TicketGroupDto;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long id;

    private LocalDate dateOfNotification;

    private LocalDate dateOfCompletion;

    private PartnerDto partner;

    private String description;

    private TicketGroupDto ticketGroup;

    private String descriptionOfSolution;

    private int workHours;

    private String reportId;

    private List<MaterialDto> materials;

}
