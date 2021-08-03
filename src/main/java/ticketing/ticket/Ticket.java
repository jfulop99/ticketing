package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticketing.partner.Partner;
import ticketing.ticketgroup.TicketGroup;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfNotification = LocalDate.now();

    private LocalDate dateOfCompletion;

    @OneToOne(targetEntity = Partner.class)
    private Partner partner;

    private String description;

    @OneToOne(targetEntity = TicketGroup.class)
    private TicketGroup ticketGroup;

    @Column(name = "desc_solution")
    private String descriptionOfSolution;

    private int workHours;

    private String reportId;

    public Ticket(LocalDate dateOfCompletion, Partner partner, String description, TicketGroup ticketGroup, String descriptionOfSolution, int workHours, String reportId) {
        this.dateOfCompletion = dateOfCompletion;
        this.partner = partner;
        this.description = description;
        this.ticketGroup = ticketGroup;
        this.descriptionOfSolution = descriptionOfSolution;
        this.workHours = workHours;
        this.reportId = reportId;
    }
}
