package fast_fix.exception_handling.exceptions;

public class FirstTestException extends RuntimeException {

    // Для индивидуального пользования

    public FirstTestException(){}
    public FirstTestException(String message){
        super(message);
    }
    public FirstTestException(String message, Throwable cause){
        super(message, cause);
    }
}
