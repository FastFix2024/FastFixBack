package fast_fix.exception_handling;

import fast_fix.exception_handling.exceptions.CustomerNotFoundException;
import fast_fix.exception_handling.exceptions.FourthTestException;
import fast_fix.exception_handling.exceptions.ThirdTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleException(ThirdTestException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }
    @ExceptionHandler(FourthTestException.class)
    public ResponseEntity<Response> handleException(FourthTestException e){
        Response response = new Response(e.getMessage(), e.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Response> handleException(CustomerNotFoundException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
