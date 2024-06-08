package com.admiralxy.commons.cqrs.request;

public interface RequestHandler<T extends Request<R>, R> {
    R handle(T request);
}
