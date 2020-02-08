package by.zazybo.domain.service.exception;

public class InvalidDateServiceException extends ServiceException {
    public InvalidDateServiceException() {
        super();
    }

    public InvalidDateServiceException(String message) {
        super(message);
    }

    public InvalidDateServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateServiceException(Throwable cause) {
        super(cause);
    }

}
