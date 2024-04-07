package smu.squiz.spring.apiPayload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import smu.squiz.spring.apiPayload.code.BaseErrorCode;
import smu.squiz.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
