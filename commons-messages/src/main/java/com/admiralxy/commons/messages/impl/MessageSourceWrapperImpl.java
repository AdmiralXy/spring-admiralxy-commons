package com.admiralxy.commons.messages.impl;

import com.admiralxy.commons.messages.MessageSourceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@RequiredArgsConstructor
public class MessageSourceWrapperImpl implements MessageSourceWrapper {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String key, Locale locale, Object... params) {
        return messageSource.getMessage(key, params, locale);
    }

    @Override
    public String getMessage(String key, Object... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }
}
