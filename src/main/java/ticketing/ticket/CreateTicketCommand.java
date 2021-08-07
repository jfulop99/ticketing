package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@CreateTicketDateValid
public class CreateTicketCommand {

    private LocalDate dateOfNotification = LocalDate.now();

    private LocalDate dateOfCompletion;

    @NotNull
    private Long partnerId;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Long ticketGroupId;

    private String descriptionOfSolution;

    @PositiveOrZero
    private int workHours;

    private String reportId;

}
