package smu.squiz.spring.apiPayload.handler;

import smu.squiz.spring.apiPayload.GeneralException;
import smu.squiz.spring.apiPayload.code.BaseErrorCode;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

