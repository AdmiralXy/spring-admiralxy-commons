package com.admiralxy.commons.messages;

import java.util.Locale;

/**
 * Message source wrapper {@see MessageSource}
 */
public interface MessageSourceWrapper {

    /**
     * Get message from file with given key.
     *
     * @param key       key, which is used to get message from file (e.g. "email.subject")
     * @param locale    locale object to be used in message (e.g. Locale.US)
     * @param params    parameters to be used in message (e.g. "John Doe")
     * @return formatted message
     */
    String getMessage(String key, Locale locale, Object... params);

    /**
     * Get message from file with given key.
     *
     * @param key       key, which is used to get message from file (e.g. "email.subject")
     * @param params    parameters to be used in message (e.g. "John Doe")
     * @return formatted message
     */
    String getMessage(String key, Object... params);
}
