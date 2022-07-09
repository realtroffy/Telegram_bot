package training_telegram_bot.demo.model;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.handlers.CallBackQueryHandler;
import training_telegram_bot.demo.handlers.MessageHandler;

@Component
public class TelegramFacade {

  private final CallBackQueryHandler callBackQueryHandler;
  private final MessageHandler messageHandler;

  public TelegramFacade(CallBackQueryHandler callBackQueryHandler, MessageHandler messageHandler) {
    this.callBackQueryHandler = callBackQueryHandler;
    this.messageHandler = messageHandler;
  }

  public BotApiMethod<?> handleUpdate(Update update) {

    if (update.hasCallbackQuery()) {
      CallbackQuery callbackQuery = update.getCallbackQuery();
      return callBackQueryHandler.process(callbackQuery);
    } else {
      Message message = update.getMessage();
      if (message != null && message.hasText()) {
        return messageHandler.process(message);
      }
    }
    return null;
  }
}
