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
@RequestMapping("/api/ticketing")
@Tag(name = "Operations on tickets")
@AllArgsConstructor
public class TicketController {

    private TicketService ticketService;

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

}
