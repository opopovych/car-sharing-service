package mate.academy.car_service.service;

import mate.academy.car_service.auth.dto.GetProfileInfoDto;
import mate.academy.car_service.auth.dto.UpdateRoleRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterResponseDto;
import mate.academy.car_service.exception.RegistrationException;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    UserRegisterResponseDto register(UserRegisterRequestDto requestDto) throws RegistrationException;

    @Transactional
    GetProfileInfoDto updateUserInfo(String email,
                                     UserRegisterRequestDto requestDto);

    void updateRole(Long userId, UpdateRoleRequestDto requestDto);

    GetProfileInfoDto getUserInfo(String email);

    void deleteUserById(Long userId);
}