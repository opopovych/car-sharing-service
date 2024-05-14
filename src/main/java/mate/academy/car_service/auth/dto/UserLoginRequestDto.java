package mate.academy.car_service.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
