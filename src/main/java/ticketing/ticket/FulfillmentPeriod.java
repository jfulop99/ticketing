package ticketing.ticket;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FulfillmentPeriod {

    private LocalDate dateOfNotification;

    private LocalDate dateOfCompletion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FulfillmentPeriod that = (FulfillmentPeriod) o;

        if (!Objects.equals(dateOfNotification, that.dateOfNotification)) return false;
        return Objects.equals(dateOfCompletion, that.dateOfCompletion);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(dateOfNotification);
        result = 31 * result + (Objects.hashCode(dateOfCompletion));
        return result;
    }
}
