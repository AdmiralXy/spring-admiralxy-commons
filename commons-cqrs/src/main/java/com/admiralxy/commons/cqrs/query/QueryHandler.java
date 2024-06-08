package com.admiralxy.commons.cqrs.query;

import com.admiralxy.commons.cqrs.request.RequestHandler;

public interface QueryHandler<T extends Query<R>, R> extends RequestHandler<T, R> {
}
