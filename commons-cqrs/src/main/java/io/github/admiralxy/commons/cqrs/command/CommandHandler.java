package io.github.admiralxy.commons.cqrs.command;

import io.github.admiralxy.commons.cqrs.request.RequestHandler;

public interface CommandHandler<T extends Command> extends RequestHandler<T, Void> {
}
