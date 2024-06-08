package com.admiralxy.commons.exception.model;

import com.admiralxy.commons.exception.dictionary.ExceptionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    /**
     * Unique identifier of the exception
     */
    private String uuid;

    /**
     * Timestamp when the exception was thrown
     */
    private Date timestamp;

    /**
     * Http status
     */
    private HttpStatus httpStatus;

    /**
     * Type of the exception
     */
    private ExceptionType type;

    /**
     * Body with data, can be null
     */
    private Object data;

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(uuid);
        out.writeObject(timestamp);
        out.writeObject(httpStatus);
        out.writeObject(type);
        out.writeObject(data);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        uuid = (String) in.readObject();
        timestamp = (Date) in.readObject();
        httpStatus = (HttpStatus) in.readObject();
        type = (ExceptionType) in.readObject();
        data = in.readObject();
    }
}
