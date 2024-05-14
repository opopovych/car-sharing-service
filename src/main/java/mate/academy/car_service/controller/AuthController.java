package mate.academy.car_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import mate.academy.car_service.auth.dto.UserLoginRequestDto;
import mate.academy.car_service.auth.dto.UserLoginResponseDto;
import mate.academy.car_service.auth.dto.UserRegisterRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterResponseDto;
import mate.academy.car_service.exception.RegistrationException;
import mate.academy.car_service.security.AuthenticationService;
import mate.academy.car_service.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth management", description = "Endpoints for registration and login")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;


    @PostMapping("/registration")
    @Operation(summary = "Endpoint for registration",description = "Endpoint for registration")
    public UserRegisterResponseDto register(@RequestBody @Valid UserRegisterRequestDto request) throws RegistrationException {
        return userService.register(request);
    }
    @PostMapping("/login")
    @Operation(summary = "Endpoint for login",description = "Endpoint for login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }


}