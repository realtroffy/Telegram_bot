package training_telegram_bot.demo.error;

public class TelegramExecuteMessageException extends RuntimeException{

    public TelegramExecuteMessageException(String message) {
        super(message);
    }
}
