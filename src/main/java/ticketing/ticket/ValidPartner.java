package ticketing.ticket;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = PartnerValidator.class)
public @interface ValidPartner {

        String message() default "Invalid partner id";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

}
