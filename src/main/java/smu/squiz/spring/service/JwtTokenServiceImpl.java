package smu.squiz.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smu.squiz.spring.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Long JwtToId(HttpServletRequest request) {
        return jwtTokenProvider.getCurrentUser(request);
    }

}

