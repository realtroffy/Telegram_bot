package training_telegram_bot.demo.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.User;


public interface UserVisitService {

    void saveToDataBase(User user);

    User getUserFromUpdate(Update update);
}
