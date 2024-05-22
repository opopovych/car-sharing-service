package mate.academy.carservice.dto.car;

import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.carservice.model.Type;

@Data
@Accessors(chain = true)
public class UpdateCarInfoRequestDto {
    private String model;
    private String brand;
    private Type type;
    private int inventory;
    private BigDecimal dailyFee;
}
