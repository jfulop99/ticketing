package ticketing.ticket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CreateTicketDateValidator implements ConstraintValidator<CreateTicketDateValid, CreateTicketCommand> {

    @Override
    public boolean isValid(CreateTicketCommand createTicketCommand, ConstraintValidatorContext constraintValidatorContext) {
        return createTicketCommand.getDateOfCompletion() == null || createTicketCommand.getDateOfNotification().isEqual(LocalDate.now()) &&
                createTicketCommand.getDateOfCompletion().isAfter(createTicketCommand.getDateOfNotification());
    }
}
