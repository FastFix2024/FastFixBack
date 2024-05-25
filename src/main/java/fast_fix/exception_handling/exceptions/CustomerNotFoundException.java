package fast_fix.exception_handling.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String customerName) {
        super(String.format("Customer with name %s not found", customerName));
    }

    public CustomerNotFoundException(Long customerId) {
        super(String.format("Customer with ID %d not found", customerId));
    }
}
