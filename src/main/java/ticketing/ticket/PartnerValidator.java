package ticketing.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import ticketing.partner.PartnerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartnerValidator implements ConstraintValidator<ValidPartner, Long> {

   @Autowired
   private PartnerRepository partnerRepository;


   @Override
   public boolean isValid(Long partnerId, ConstraintValidatorContext context) {
      return partnerRepository.findById(partnerId).isPresent();
   }
}
