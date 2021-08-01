package ticketing.ticket;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.partner.PartnerRepository;
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

    public TicketDto createTicket(createTicketCommand command) {
        Ticket ticket = new Ticket(command.getDateOfCompletion(), partnerRepository.getById(command.getPartnerId()), command.getDescription(),
                ticketGroupRepository.getById(command.getTicketGroupId()), command.getDescriptionOfSolution(), command.getWorkHours(), command.getReportId());
        ticketRepository.save(ticket);
        Ticket saved = ticketRepository.findById(ticket.getId()).orElseThrow();
        return modelMapper.map(saved, TicketDto.class);
    }

    public List<TicketDto> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }
}
