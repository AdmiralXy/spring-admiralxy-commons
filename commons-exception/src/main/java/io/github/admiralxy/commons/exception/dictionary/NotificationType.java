package io.github.admiralxy.commons.exception.dictionary;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationType {

    /**
     * Internal error
     */
    INTERNAL_ERROR,

    /**
     * Error
     */
    ERROR,

    /**
     * Warning
     */
    WARNING,

    /**
     * Info
     */
    INFO,

    /**
     * Success
     */
    SUCCESS;

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }
}
