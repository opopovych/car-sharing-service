package mate.academy.carservice.dto.car;

import lombok.Data;
import lombok.experimental.Accessors;
import mate.academy.carservice.model.Type;

@Data
@Accessors(chain = true)
public class CarDto {
    private String model;
    private String brand;
    private Type type;
}
