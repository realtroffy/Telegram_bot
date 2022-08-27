package telegrambot.error;

public class TelegramExecuteMessageException extends RuntimeException{

    public TelegramExecuteMessageException(String message) {
        super(message);
    }
}
