package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.dictionary.ExceptionType;
import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import io.github.admiralxy.commons.exception.model.ValidationError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
public class MsValidationException extends MsException {

    public MsValidationException(Map<String, List<String>> fields) {
        super(ExceptionMessage.builder()
                .type(ExceptionType.VALIDATION)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(ValidationError.of(fields))
                .build());
    }

    public MsValidationException(String field, List<String> messages) {
        super(ExceptionMessage.builder()
                .type(ExceptionType.VALIDATION)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(ValidationError.of(field, messages))
                .build());
    }

    public MsValidationException(String field, String message) {
        super(ExceptionMessage.builder()
                .type(ExceptionType.VALIDATION)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(ValidationError.of(field, message))
                .build());
    }
}
