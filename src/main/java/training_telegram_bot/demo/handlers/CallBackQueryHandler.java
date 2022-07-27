package training_telegram_bot.demo.handlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class CallBackQueryHandler {

  public Message process(CallbackQuery callbackQuery) {
    return null;
  }
}
