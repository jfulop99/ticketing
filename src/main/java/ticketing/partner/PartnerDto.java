package ticketing.partner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDto {
    private Long id;

    private String name;

    private String accountingId;

    @Embedded
    @AttributeOverride(name = "zip", column = @Column(name = "postal_code"))
    private Address address;

}
