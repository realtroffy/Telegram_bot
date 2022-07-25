package training_telegram_bot.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.handlers.CallBackQueryHandler;
import training_telegram_bot.demo.handlers.MessageHandler;

@Service
@AllArgsConstructor
public class TelegramFacade {

  private final CallBackQueryHandler callBackQueryHandler;
  private final MessageHandler messageHandler;

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
