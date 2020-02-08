package by.zazybo.domain.service.exception;

public class DataBaseConnectionServiceException extends ServiceException {
    public DataBaseConnectionServiceException() {
        super();
    }

    public DataBaseConnectionServiceException(String message) {
        super(message);
    }

    public DataBaseConnectionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseConnectionServiceException(Throwable cause) {
        super(cause);
    }

}
