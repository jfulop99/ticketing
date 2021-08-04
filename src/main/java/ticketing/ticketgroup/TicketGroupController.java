package ticketing.ticketgroup;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/groups")
@Tag(name = "Operations on groups")
@AllArgsConstructor
public class TicketGroupController {

    private final TicketGroupService ticketGroupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a group")
    @ApiResponse(responseCode = "201", description = "Group has been created")
    public TicketGroupDto createGroup(@Valid @RequestBody CreateTicketGroupCommand command){
        return ticketGroupService.createGroup(command);
    }

    @GetMapping
    @Operation(summary = "List all groups")
    public List<TicketGroupDto> getGroups(){
        return ticketGroupService.getGroups();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find group by id")
    public TicketGroupDto getGroupById(@PathVariable Long id){
        return ticketGroupService.findGroupById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a group by id")
    public TicketGroupDto updateGroupById(@PathVariable Long id, @Valid @RequestBody UpdateGroupCommand command){
        return ticketGroupService.updateGroupById(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a group by id")
    public void deleteGroupById(@PathVariable Long id){
        ticketGroupService.deleteGroupById(id);
    }


}
