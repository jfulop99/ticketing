package ticketing.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class createPartnerCommand {

    @NotNull
    @NotBlank
    @Length(min = 3, max = 50)
    @Schema(example = "Hotel Intercontinental Budapest")
    private String name;

    @NotNull
    @NotBlank
    @Schema(example = "0025")
    private String accountingId;

    @NotNull
    private Address address;

}
