package telegrambot.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.model.UserWriteBot;

public interface WebHookService {

  UserWriteBot saveToDataBase(UserWriteBot userWriteBot);

  UserWriteBot getUserFromUpdate(Update update);
}
