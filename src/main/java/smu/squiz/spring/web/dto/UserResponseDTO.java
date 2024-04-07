package smu.squiz.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class UserResponseDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class JoinResultDTO {

        String email;
        LocalDate createAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResDTO {

        String type;
        String accessToken;
        String refreshToken;
        String tokenExpiresTime;

    }
}
