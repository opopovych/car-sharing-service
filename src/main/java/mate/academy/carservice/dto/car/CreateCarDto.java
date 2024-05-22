package mate.academy.carservice.dto.car;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.carservice.model.Type;

@Data
@NotNull
@Accessors(chain = true)
public class CreateCarDto {
    private String model;
    private String brand;
    private Type type;
    private int inventory;
    private BigDecimal dailyFee;
}
