package ticketing.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @NotNull
    @NotBlank
    @Schema(example = "H-1051")
    private String zip;

    @NotNull
    @NotBlank
    @Schema(example = "Budapest")
    private String city;

    @NotNull
    @NotBlank
    @Schema(example = "Széchenyi tér")
    private String addressLine1;

    @Schema(example = "null")
    private String addressLine2;

}
