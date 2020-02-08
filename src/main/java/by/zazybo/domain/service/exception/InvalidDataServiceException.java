package by.zazybo.domain.service.exception;

public class InvalidDataServiceException extends ServiceException {
    public InvalidDataServiceException() {
        super();
    }

    public InvalidDataServiceException(String message) {
        super(message);
    }

    public InvalidDataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataServiceException(Throwable cause) {
        super(cause);
    }

}
