package mate.academy.car_service.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateRoleRequestDto {
    @NotNull
    @Positive
    private Long roleId;
}
