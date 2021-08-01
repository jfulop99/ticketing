package ticketing.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import ticketing.partner.PartnerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartnerValidator implements ConstraintValidator<ValidPartner, Long> {

   @Autowired
   private PartnerRepository partnerRepository;

   public void initialize(ValidPartner constraint) {
   }

   public boolean isValid(Long partner_id, ConstraintValidatorContext context) {
      return partnerRepository.findById(partner_id).isPresent();
   }
}
