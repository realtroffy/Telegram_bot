package training_telegram_bot.demo.service;

import org.springframework.data.domain.Page;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.UserWriteBot;

public interface UserVisitService {

  void saveToDataBase(UserWriteBot userWriteBot);

  UserWriteBot getUserFromUpdate(Update update);
}
