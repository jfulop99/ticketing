package ticketing.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ticketing.material.CreateMaterialCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Operations on tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Create a ticket")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Ticket has been created")
    public TicketDto createTicket(@Valid @RequestBody CreateTicketCommand command){
        return ticketService.createTicket(command);
    }

    @GetMapping
    @Operation(summary = "Get all tickets")
    public List<TicketDto> findAllTickets(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a ticket by id")
    public TicketDto getTicketById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ticket")
    public TicketDto updateTicketById(@PathVariable Long id, @Valid @RequestBody UpdateTicketCommand command){
        return ticketService.updateTicket(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ticket by id")
    public void deleteTicketById(@PathVariable Long id){
        ticketService.deleteTicketById(id);
    }

    @PostMapping("/{id}/materials")
    @Operation(summary = "Add a material to ticket by id")
    public TicketDto addMaterialToTicket(@PathVariable Long id, @Valid @RequestBody CreateMaterialCommand command){
        return ticketService.addMaterialToTicket(id, command);
    }

}
