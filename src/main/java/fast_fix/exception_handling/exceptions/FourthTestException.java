package fast_fix.exception_handling.exceptions;

public class FourthTestException extends RuntimeException {

    public FourthTestException(String message){
        super(message);
    }
    public FourthTestException(String message, Throwable cause){
        super(message, cause);
    }
}
