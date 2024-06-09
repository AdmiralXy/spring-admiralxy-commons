package io.github.admiralxy.commons.exception;

import io.github.admiralxy.commons.exception.dictionary.ExceptionType;
import io.github.admiralxy.commons.exception.dictionary.NotificationType;
import io.github.admiralxy.commons.exception.model.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class MsNotificationException extends MsException {

    private static final String NOTIFICATION_TYPE_FIELD = "notificationType";

    public MsNotificationException(NotificationType notificationType, String message) {
        super(ExceptionMessage.builder()
                .type(ExceptionType.NOTIFICATION)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(Map.of(
                        NOTIFICATION_TYPE_FIELD, notificationType,
                        MESSAGE_FIELD, message
                ))
                .build());
    }
}
