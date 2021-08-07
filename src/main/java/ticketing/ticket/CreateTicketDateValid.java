package ticketing.ticket;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreateTicketDateValidator.class)
public @interface CreateTicketDateValid {

    String message() default "Invalid date of completion";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
