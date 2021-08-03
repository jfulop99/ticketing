package ticketing.partner;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    private final ModelMapper modelMapper;

    public List<PartnerDto> findAllPartners() {
        return partnerRepository.findAll()
                .stream()
                .map(partner -> modelMapper.map(partner, PartnerDto.class))
                .collect(Collectors.toList());
    }

    public PartnerDto findPartnerById(Long id) {
        return modelMapper.map(getPartnerById(id), PartnerDto.class);
    }

    private Partner getPartnerById(Long id) {
        return partnerRepository
                .findById(id).orElseThrow(() -> new PartnerNotFoundException("Cannot find partner id = " + id));
    }

    public PartnerDto createPartner(CreatePartnerCommand command) {
        Partner partner = new Partner(command.getName(), command.getAccountingId(), command.getAddress());
        partnerRepository.save(partner);
        return modelMapper.map(partner, PartnerDto.class);
    }

    public PartnerDto updatePartnerById(Long id, UpdatePartnerCommand command) {
        Partner partner = getPartnerById(id);
        partner.setName(command.getName());
        partner.setAccountingId(command.getAccountingId());
        partner.setAddress(command.getAddress());
        partnerRepository.save(partner);
        return modelMapper.map(partner, PartnerDto.class);
    }

    public void deletePartnerById(Long id) {
        Partner partner = getPartnerById(id);
        partnerRepository.deleteById(partner.getId());
    }
}
