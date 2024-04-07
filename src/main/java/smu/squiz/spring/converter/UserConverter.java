package smu.squiz.spring.converter;

import smu.squiz.spring.domain.User;
import smu.squiz.spring.domain.enums.Gender;
import smu.squiz.spring.domain.enums.RoleType;
import smu.squiz.spring.web.dto.UserRequestDTO;
import smu.squiz.spring.web.dto.UserResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO joinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .email(user.getEmail())
                .createAt(LocalDate.from(LocalDateTime.now()))
                .build();

    }
    public static User toUser(UserRequestDTO.UserJoinRequest userJoinRequest, String pw) {

        Gender gender = null;

        switch (userJoinRequest.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }
        return User.builder()
                .email(userJoinRequest.getEmail())
                .password(pw)
                .gender(gender)
                .is_deleted(userJoinRequest.is_deleted())
                .role(RoleType.USER)
                .build();
    }
}
