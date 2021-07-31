package ticketing.ticketgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketGroupRepository extends JpaRepository<TicketGroup, Long> {
}
