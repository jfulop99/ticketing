package ticketing.partner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/partners")
@Tag(name = "Operations on partners")
@AllArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping
    @Operation(summary = "Get all partners")
    public List<PartnerDto> findAllPartners(){
        return partnerService.findAllPartners();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find partner by id")
    public PartnerDto findPartnerById(@PathVariable Long id){
        return partnerService.findPartnerById(id);
    }

    @PostMapping
    @Operation(summary = "Create a partner")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Partner has been created")
    public PartnerDto createPartner(@Valid @RequestBody CreatePartnerCommand command){
        return partnerService.createPartner(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a partner by id")
    public PartnerDto updatePartnerById(@PathVariable Long id, @Valid @RequestBody UpdatePartnerCommand command){
        return partnerService.updatePartnerById(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a partner by id")
    public void deletePartnerById(@PathVariable Long id){
        partnerService.deletePartnerById(id);
    }


}
