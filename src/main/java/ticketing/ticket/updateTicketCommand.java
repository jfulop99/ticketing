package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateTicketCommand {

    private LocalDate dateOfCompletion;

    @NotNull
    @NotBlank
    private String description;

    private String descriptionOfSolution;

    @PositiveOrZero
    private int workHours;

    private String reportId;

}
