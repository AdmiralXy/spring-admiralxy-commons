package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.dictionary.ExceptionType;
import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MsAccessDeniedException extends MsException {

    public MsAccessDeniedException() {
        super(ExceptionMessage.builder()
                .type(ExceptionType.OTHER)
                .httpStatus(HttpStatus.FORBIDDEN)
                .build());
    }
}
