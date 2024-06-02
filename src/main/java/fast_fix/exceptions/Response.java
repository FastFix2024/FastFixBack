package fast_fix.exceptions;

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

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (!Objects.equals(message, response.message)) return false;
        return Objects.equals(additionalMessage, response.additionalMessage);
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (additionalMessage != null ? additionalMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", additionalMessage='" + additionalMessage + '\'' +
                '}';
    }
}