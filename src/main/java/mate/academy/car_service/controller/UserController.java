package mate.academy.car_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import mate.academy.car_service.auth.dto.GetProfileInfoDto;
import mate.academy.car_service.auth.dto.UpdateRoleRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterRequestDto;
import mate.academy.car_service.model.User;
import mate.academy.car_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User management", description = "Endpoints for managing users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/me")
    @Operation(summary = "User's information",
            description = "Endpoint for getting information about user")
    public GetProfileInfoDto getUserInfo(Authentication authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        User user = (User) userDetails;
        return userService.getUserInfo(user.getEmail());
    }

    @Operation(summary = "Updating user's information",
            description = "Endpoint for updating user's information ")
    @PutMapping("/me")
    public GetProfileInfoDto UpdateUserInfo(Authentication authentication, @RequestBody UserRegisterRequestDto requestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        User user = (User) userDetails;
        return userService.updateUserInfo(user.getEmail(), requestDto);
    }

    @Operation(summary = "Updating user's role",
            description = "Endpoint for updating user's role")
    @PatchMapping("/{id}/role")
    public void updateUserRole(@PathVariable Long id, @RequestBody @Valid UpdateRoleRequestDto requestDto) {
        userService.updateRole(id, requestDto);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Deleting an user by id",
            description = "Endpoint for deleting an user by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

}