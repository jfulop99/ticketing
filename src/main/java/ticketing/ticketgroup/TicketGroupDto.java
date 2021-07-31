package ticketing.ticketgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketGroupDto {

    @Schema(description = "id of the group", example = "1")
    private Long id;

    @Schema(description = "name of the group", example = "Antenna fejállomás")
    private String group;

}
