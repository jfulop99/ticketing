package ticketing.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticketing.ticket.Ticket;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
//    @JsonIgnore
    private Ticket ticket;

    public Material(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
