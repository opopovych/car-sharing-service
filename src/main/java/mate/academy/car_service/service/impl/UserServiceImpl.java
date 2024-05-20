package mate.academy.car_service.service.impl;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import mate.academy.car_service.auth.dto.GetProfileInfoDto;
import mate.academy.car_service.auth.dto.UpdateRoleRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterResponseDto;
import mate.academy.car_service.exception.RegistrationException;
import mate.academy.car_service.mapper.UserMapper;
import mate.academy.car_service.model.Role;
import mate.academy.car_service.model.User;
import mate.academy.car_service.repo.RoleRepository;
import mate.academy.car_service.repo.UserRepository;
import mate.academy.car_service.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder
            passwordEncoder;

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail()
                    + " already exists");
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.getRoles().add(roleRepository.findByRoleName(Role.RoleName.ROLE_MANAGER));

        User savedUser = userRepository.save(user);
        return userMapper.entityToRegisterResponseDto(savedUser);
    }

    @Override
    public GetProfileInfoDto getUserInfo(String email) {
        User user = findUserByEmail(email);
        return userMapper.entityToUserInfoResponseDto(user);
    }

    @Override
    @Transactional
    public GetProfileInfoDto updateUserInfo(String email,
                                                 UserRegisterRequestDto requestDto) {
        User user = findUserByEmail(email);
        User updatedUser = userMapper.updateUserInfo(user, requestDto);
        User savedUpdatedUser = userRepository.save(updatedUser);
        return userMapper.entityToUserInfoResponseDto(savedUpdatedUser);
    }

    @Override
    @Transactional
    public void updateRole(Long userId,
                           UpdateRoleRequestDto requestDto) {
        checkManagerId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id "
                        + userId + " was not found"));
        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Role with id "
                        + requestDto.getRoleId() + " was not found"));

        user.getRoles().clear();
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        checkManagerId(userId);
        userRepository.deleteById(userId);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email "
                        + email + " was not found"));
    }

    private void checkManagerId(Long userId) {
        if (userId == 1) {
            throw new RuntimeException("Manager with id 1 can not do any updates with yourself");
        }
    }
}