package pl.careaboutit.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends  RuntimeException {

    @Getter
    private final HttpStatus status;
    private final String message;

    public BusinessException(String message) {
        super(message);

        this.status = HttpStatus.NOT_FOUND;
        this.message = message;
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);

        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
