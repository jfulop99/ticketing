package ticketing.ticket;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = DateOfCompletionValidator.class)
public @interface ThenOrAfterDateOfNotification {

    String message() default "Invalid date of completion";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
