package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticketing.material.Material;
import ticketing.partner.Partner;
import ticketing.ticketgroup.TicketGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FulfillmentPeriod fulfillmentPeriod;

    @OneToOne(targetEntity = Partner.class)
    private Partner partner;

    private String description;

    @OneToOne(targetEntity = TicketGroup.class)
    private TicketGroup ticketGroup;

    @Column(name = "desc_solution")
    private String descriptionOfSolution;

    private int workHours;

    private String reportId;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materials;

    public Ticket(FulfillmentPeriod fulfillmentPeriod, Partner partner, String description, TicketGroup ticketGroup, String descriptionOfSolution, int workHours, String reportId) {
        this.fulfillmentPeriod = fulfillmentPeriod;
        this.partner = partner;
        this.description = description;
        this.ticketGroup = ticketGroup;
        this.descriptionOfSolution = descriptionOfSolution;
        this.workHours = workHours;
        this.reportId = reportId;
    }

    public void addMaterial(Material material){
        if(materials == null){
            materials = new ArrayList<>();
        }
        materials.add(material);
        material.setTicket(this);
    }
}
