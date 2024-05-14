package mate.academy.car_service.mapper;

import mate.academy.car_service.auth.dto.GetProfileInfoDto;
import mate.academy.car_service.auth.dto.UserRegisterRequestDto;
import mate.academy.car_service.auth.dto.UserRegisterResponseDto;
import mate.academy.car_service.config.MapperConfig;
import mate.academy.car_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegisterResponseDto entityToRegisterResponseDto(User savedUser);

    GetProfileInfoDto entityToUserInfoResponseDto(User user);

    @Mapping(target = "firstName",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserInfo(@MappingTarget User user, UserRegisterRequestDto requestDto);
}