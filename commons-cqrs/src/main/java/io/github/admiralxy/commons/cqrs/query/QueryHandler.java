package io.github.admiralxy.commons.cqrs.query;

import io.github.admiralxy.commons.cqrs.request.RequestHandler;

public interface QueryHandler<T extends Query<R>, R> extends RequestHandler<T, R> {
}
