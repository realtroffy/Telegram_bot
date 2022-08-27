package telegrambot.error;

public class GetConnectionException extends RuntimeException{

    public GetConnectionException(String message) {
        super(message);
    }
}
