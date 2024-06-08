package com.admiralxy.commons.exception.dictionary;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExceptionType {

    /**
     * Technical errors (e.g. database connection error)
     */
    INTERNAL,

    /**
     * Notification errors (e.g. email sending error)
     */
    NOTIFICATION,

    /**
     * Validation errors (e.g. invalid input data)
     */
    VALIDATION,

    /**
     * Business errors (e.g. insufficient balance)
     */
    BUSINESS,

    /**
     * Other errors
     */
    OTHER;

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }
}
