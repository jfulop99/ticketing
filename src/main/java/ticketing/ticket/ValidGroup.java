package ticketing.ticket;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = GroupValidator.class)
public @interface ValidGroup {

    String message() default "Invalid group id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
