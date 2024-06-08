package com.admiralxy.commons.cqrs.mediator;

import com.admiralxy.commons.cqrs.request.Request;
import com.admiralxy.commons.cqrs.request.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mediator {

    private static final String HANDLER_POSTFIX = "Handler";

    private final ApplicationContext context;

    public <R, T extends Request<R>> R send(T request) {
        var handlerName = request.getClass().getSimpleName() + HANDLER_POSTFIX;
        var handlerBean = context.getBean(handlerName);

        if (handlerBean instanceof RequestHandler<?, ?>) {
            @SuppressWarnings("unchecked")
            var handler = (RequestHandler<T, R>) handlerBean;
            return handler.handle(request);
        }

        throw new IllegalStateException("Handler not found for " + request.getClass().getName());
    }
}
