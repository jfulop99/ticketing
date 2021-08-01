package ticketing.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import ticketing.ticketgroup.TicketGroupRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupValidator implements ConstraintValidator<ValidGroup, Long> {

    @Autowired
    private TicketGroupRepository ticketGroupRepository;

    @Override
    public boolean isValid(Long groupId, ConstraintValidatorContext constraintValidatorContext) {
        return ticketGroupRepository.findById(groupId).isPresent();
    }
}
