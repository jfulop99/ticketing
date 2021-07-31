package ticketing.ticketgroup;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketGroupService {

    private final TicketGroupRepository ticketGroupRepository;

    private final ModelMapper modelMapper;


    public TicketGroupDto createGroup(createTicketGroupCommand command) {

        TicketGroup ticketGroup = new TicketGroup(command.getGroup());

        ticketGroupRepository.save(ticketGroup);

        return modelMapper.map(ticketGroup, TicketGroupDto.class);
    }

    public List<TicketGroupDto> getGroups() {
        return ticketGroupRepository.findAll()
                .stream()
                .map(group -> modelMapper.map(group, TicketGroupDto.class))
                .collect(Collectors.toList());
    }

    public TicketGroupDto updateGroupById(Long id, updateGroupCommand command) {
        TicketGroup ticketGroup = findTicketGroupById(id);
        ticketGroup.setGroup(command.getGroup());
        ticketGroupRepository.save(ticketGroup);
        return modelMapper.map(ticketGroup, TicketGroupDto.class);
    }

    private TicketGroup findTicketGroupById(Long id) {
        return ticketGroupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Cannot find group id = " + id));
    }

    public void deleteGroupById(Long id) {
        ticketGroupRepository.deleteById(id);
    }

    public TicketGroupDto findGroupById(Long id) {
        return modelMapper.map(findTicketGroupById(id), TicketGroupDto.class);
    }
}
