package fast_fix.exception_handling.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super(String.format("User with name %s not found", username));
    }

    public UserNotFoundException(Long userId) {
        super(String.format("User with ID %d not found", userId));
    }
}
