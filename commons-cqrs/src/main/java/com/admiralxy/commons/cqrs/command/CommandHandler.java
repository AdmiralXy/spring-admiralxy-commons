package com.admiralxy.commons.cqrs.command;

import com.admiralxy.commons.cqrs.request.RequestHandler;

public interface CommandHandler<T extends Command> extends RequestHandler<T, Void> {
}
