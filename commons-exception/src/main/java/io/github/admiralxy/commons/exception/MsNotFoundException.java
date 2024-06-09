package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.dictionary.ExceptionType;
import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MsNotFoundException extends MsException {

    public MsNotFoundException() {
        super(ExceptionMessage.builder()
                .type(ExceptionType.OTHER)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
    }
}
