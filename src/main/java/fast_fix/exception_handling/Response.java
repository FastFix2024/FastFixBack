package fast_fix.exception_handling;

import java.util.Objects;

public class Response {

    private String message;
    private String additionalMessage;

    public Response(String message) {
        this.message = message;
    }
    public Response(String message, String additionalMessage) {
        this.message = message;
        this.additionalMessage = additionalMessage;
    }

    public String getMessage() {
        return message;
    }
    public String getAdditionalMessage() {
        return additionalMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response responce = (Response) o;
        return Objects.equals(message, responce.message) && Objects.equals(additionalMessage, responce.additionalMessage);
    }
    @Override
    public int hashCode() {
        int result = Objects.hashCode(message);
        result = 31 * result + Objects.hashCode(additionalMessage);
        return result;
    }

    @Override
    public String toString() {
        return "Responce{" +
                "message='" + message + '\'' +
                ", additionalMessage='" + additionalMessage + '\'' +
                '}';
    }
}
