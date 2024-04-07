package smu.squiz.spring.service;

import smu.squiz.spring.domain.User;
import smu.squiz.spring.web.dto.TokenDTO;
import smu.squiz.spring.web.dto.UserRequestDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User joinUser(UserRequestDTO.UserJoinRequest userJoinRequest);

    List<TokenDTO> login(UserRequestDTO.UserLoginRequest userLoginRequest);

    void checkUser(Boolean flag);

    List<TokenDTO> reissue(HttpServletRequest request);

//    String getEmail(HttpServletRequest request);

    String logout(HttpServletRequest request);


    String deactivateUser(HttpServletRequest request);
}
