package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.dictionary.ExceptionType;
import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MsTechnicalException extends MsException {

    public MsTechnicalException() {
        super(ExceptionMessage.builder()
                .type(ExceptionType.INTERNAL)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build());
    }
}
