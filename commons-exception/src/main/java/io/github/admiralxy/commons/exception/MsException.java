package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter
public class MsException extends RuntimeException {

    protected static final String MESSAGE_FIELD = "message";

    protected final transient ExceptionMessage body;

    public MsException(ExceptionMessage body) {
        body.setUuid(UUID.randomUUID().toString());
        body.setTimestamp(Date.from(Instant.now()));

        this.body = body;
    }
}
