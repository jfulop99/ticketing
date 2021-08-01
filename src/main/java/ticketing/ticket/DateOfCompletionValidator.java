package ticketing.ticket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.time.LocalDate;

public class DateOfCompletionValidator implements ConstraintValidator<ThenOrAfterDateOfNotification, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
