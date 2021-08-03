package ticketing.ticketgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTicketGroupCommand {

    @NotNull
    @NotBlank
    @Length(max = 25, min = 2)
    @Schema(description = "name of the group", example = "Antenna fejállomás")
    private String group;

}
