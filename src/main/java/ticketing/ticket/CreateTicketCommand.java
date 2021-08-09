package ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTicketCommand {

    @NotNull
    @FulfillmentPeriodValid
    private FulfillmentPeriod fulfillmentPeriod;

    @NotNull
    @ValidPartner
    private Long partnerId;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @ValidGroup
    private Long ticketGroupId;

    private String descriptionOfSolution;

    @PositiveOrZero
    private int workHours;

    private String reportId;

}
