package ticketing.ticket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FulfillmentPeriodValidator implements ConstraintValidator<FulfillmentPeriodValid, FulfillmentPeriod> {

    private PeriodType periodType;

    @Override
    public void initialize(FulfillmentPeriodValid constraintAnnotation) {
        periodType = constraintAnnotation.periodType();
    }

    @Override
    public boolean isValid(FulfillmentPeriod fulfillmentPeriod, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = fulfillmentPeriod.getDateOfNotification();
        LocalDate endDate = fulfillmentPeriod.getDateOfCompletion();
        if (periodType == PeriodType.CREATE && (startDate == null || !startDate.isEqual(LocalDate.now()))){
            return false;
        }
        if (periodType == PeriodType.UPDATE && startDate == null){
            return false;
        }
        return endDate == null || startDate.isBefore(endDate) || startDate.isEqual(endDate);
    }
}
