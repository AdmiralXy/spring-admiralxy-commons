package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.dictionary.ExceptionType;
import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class MsBusinessException extends MsException {

    public MsBusinessException(String message) {
        super(ExceptionMessage.builder()
                .type(ExceptionType.BUSINESS)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(Map.of(MESSAGE_FIELD, message))
                .build());
    }
}
