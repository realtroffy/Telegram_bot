package training_telegram_bot.demo.handlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
public class CallBackQueryHandler {

  public BotApiMethod<?> process(CallbackQuery callbackQuery) {
    return null;
  }
}
