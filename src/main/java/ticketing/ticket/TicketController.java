package ticketing.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Operations on tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @Operation(description = "Create a ticket")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Ticket has been created")
    public TicketDto createTicket(@Valid @RequestBody createTicketCommand command){
        return ticketService.createTicket(command);
    }

    @GetMapping
    @Operation(description = "Get all tickets")
    public List<TicketDto> findAllTickets(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Get a ticket by id")
    public TicketDto getTicketById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update a ticket")
    public TicketDto updateTicketById(@PathVariable Long id, @Valid @RequestBody updateTicketCommand command){
        return ticketService.updateTicket(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete ticket by id")
    public void deleteTicketById(@PathVariable Long id){
        ticketService.deleteTicketById(id);
    }

}
