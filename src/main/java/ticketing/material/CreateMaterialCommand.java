package ticketing.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaterialCommand {

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    private String name;

    @NotNull
    @PositiveOrZero
    private int price;

}
