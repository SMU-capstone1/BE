package smu.squiz.spring.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smu.squiz.spring.apiPayload.ApiResponse;
import smu.squiz.spring.apiPayload.GeneralException;
import smu.squiz.spring.converter.UserConverter;
import smu.squiz.spring.domain.User;
import smu.squiz.spring.repository.UserRepository;
import smu.squiz.spring.service.JwtTokenService;
import smu.squiz.spring.service.UserService;
import smu.squiz.spring.web.dto.TokenDTO;
import smu.squiz.spring.web.dto.UserRequestDTO;
import smu.squiz.spring.web.dto.UserResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/join")//회원가입
    @Operation(summary = "회원가입 API", description = "회원가입하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4002", description = "NOT_ACCEPTABLE, 이미 존재하는 메일 주소입니다."),
    })
    @Parameters({})
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.UserJoinRequest userJoinRequest) {

        User user = userService.joinUser(userJoinRequest);
        return ApiResponse.onSuccess(UserConverter.joinResultDTO(user));

    }

    //로그인
    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "로그인하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4010", description = "NOT_FOUND, 가입 가능한 이메일입니다.(이메일 정보가 존재하지 않습니다.)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4008", description = "NOT_FOUND, 비밀번호가 틀렸습니다."),
    })
    public ApiResponse<List<TokenDTO>> login(@RequestBody UserRequestDTO.UserLoginRequest userLoginRequest) throws GeneralException {
        List<TokenDTO> tokenDTOList = userService.login(userLoginRequest);
        return ApiResponse.onSuccess(tokenDTOList);
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    @Operation(summary = "토큰 재발급 API", description = "토큰 재발급하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4007", description = "UNAUTHORIZED, 유효하지 않은 JWT입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4100", description = "UNAUTHORIZED, RefreshToken값을 확인해주세요."),
    })
    @Parameters({
            @Parameter(name = "atk", description = "RequestHeader - 로그인한 사용자의 accessToken"),
    })
    public ApiResponse<List<TokenDTO>> reissue(@RequestHeader(name = "rtk") String rtk, HttpServletRequest request) {
        System.out.println("controller: reissue 함수 실행");
        return ApiResponse.onSuccess(userService.reissue(request));
    }

    // 로그아웃
    @PostMapping("/logout")
    @Operation(summary = "로그아웃 API", description = "로그아웃하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "NOT_FOUND, 회원정보가 존재하지 않습니다."),
    })
    @Parameters({
            @Parameter(name = "atk", description = "RequestHeader - 로그인한 사용자의 accessToken"),
    })
    public ApiResponse<String> logout(@RequestHeader(name = "atk") String atk, HttpServletRequest request) {
        return ApiResponse.onSuccess(userService.logout(request));
    }

    // 회원 탈퇴
    @DeleteMapping("/delete")
    @Operation(summary = "회원 탈퇴 API", description = "회원을 탈퇴시키는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "NOT_FOUND, 회원정보가 존재하지 않습니다."),
    })
    @Parameters({
            @Parameter(name = "atk", description = "RequestHeader - 회원 탈퇴할 회원의 accessToken"),
    })
    public ApiResponse<String> deactivateUser(@RequestHeader(name = "atk") String atk, HttpServletRequest request) throws GeneralException {
        return ApiResponse.onSuccess(userService.deactivateUser(request));
    }
}