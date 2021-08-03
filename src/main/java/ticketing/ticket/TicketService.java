package ticketing.ticket;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.material.CreateMaterialCommand;
import ticketing.material.Material;
import ticketing.partner.Partner;
import ticketing.partner.PartnerRepository;
import ticketing.ticketgroup.TicketGroup;
import ticketing.ticketgroup.TicketGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final PartnerRepository partnerRepository;

    private final TicketGroupRepository ticketGroupRepository;

    private final ModelMapper modelMapper;

    public TicketDto createTicket(CreateTicketCommand command) {
        Partner partner = partnerRepository.getById(command.getPartnerId());
        TicketGroup ticketGroup = ticketGroupRepository.getById(command.getTicketGroupId());

        Ticket ticket = new Ticket(command.getDateOfCompletion(), partner, command.getDescription(),
                ticketGroup, command.getDescriptionOfSolution(), command.getWorkHours(), command.getReportId());
        ticketRepository.saveAndFlush(ticket);
        return modelMapper.map(ticket, TicketDto.class);
    }

    public List<TicketDto> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    public TicketDto getTicketById(Long id) {
        return modelMapper.map(findTicketById(id), TicketDto.class);
    }

    private Ticket findTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find ticket"));
    }

    public TicketDto updateTicket(Long id, UpdateTicketCommand command) {
        Ticket ticket = ticketRepository.getById(id);
        ticket.setDateOfCompletion(command.getDateOfCompletion());
        ticket.setDescription(command.getDescription());
        ticket.setDescriptionOfSolution(command.getDescriptionOfSolution());
        ticket.setWorkHours(command.getWorkHours());
        ticket.setReportId(command.getReportId());
        ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketDto.class);
    }

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Transactional
    public TicketDto addMaterialToTicket(Long id, CreateMaterialCommand command) {
        Ticket ticket = findTicketById(id);
        Material material = new Material(command.getName(), command.getPrice());
        ticket.addMaterial(material);
        return modelMapper.map(ticket, TicketDto.class);
    }
}
