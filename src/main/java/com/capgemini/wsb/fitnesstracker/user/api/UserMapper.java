package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.api.dtos.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dtos.UserEmailDto;
import com.capgemini.wsb.fitnesstracker.user.api.dtos.UserSimpleDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    public UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(),
                user.getEmail());
    }
}
