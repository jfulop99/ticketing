package ticketing.partner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String accountingId;

    @Embedded
    @AttributeOverride(name = "zip", column = @Column(name = "postal_code"))
    private Address address;

    public Partner(String name, String accountingId, Address address) {
        this.name = name;
        this.accountingId = accountingId;
        this.address = address;
    }
}
