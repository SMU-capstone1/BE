package smu.squiz.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import smu.squiz.spring.apiPayload.code.BaseErrorCode;
import smu.squiz.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseErrorCode {
    // 가장 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "요청에 성공했습니다."),
    ;

    // 멤버 관련 응답

    // ~~~ 관련 응답 ....


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
