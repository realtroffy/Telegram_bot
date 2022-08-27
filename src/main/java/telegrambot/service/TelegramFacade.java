package telegrambot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.handlers.MessageHandler;

@Service
@AllArgsConstructor
public class TelegramFacade {

  private final MessageHandler messageHandler;

  public Message handleUpdate(Update update) {
    Message message = update.getMessage();
    return messageHandler.process(message);
  }
}
