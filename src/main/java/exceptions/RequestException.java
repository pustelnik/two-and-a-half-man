package exceptions;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class RequestException extends RuntimeException {
    public RequestException(String message) {
        super(message);
    }
    public RequestException(String message,Throwable cause) {
        super(message, cause);
    }
}

