package smu.squiz.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequestDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserJoinRequest {

        String id;
        String password;
        String email;
        Integer gender;
        boolean is_deleted;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserLoginRequest {//로그인 요청
        String email;
        String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserDeleteReq { // 회원 탈퇴 요청
        Long userIdx;
    }
}
