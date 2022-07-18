package training_telegram_bot.demo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.UserWriteBot;
import training_telegram_bot.demo.repository.UserVisitRepository;
import training_telegram_bot.demo.service.UserVisitService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserVisitServiceImpl implements UserVisitService {

  private final UserVisitRepository userVisitRepository;

  @Override
  public void saveToDataBase(UserWriteBot userWriteBot) {
    userVisitRepository.save(userWriteBot);
  }

  @Override
  public UserWriteBot getUserFromUpdate(Update update) {
    String firstName = update.getMessage().getFrom().getFirstName();
    String lastName = update.getMessage().getFrom().getLastName();
    String buttonName = update.getMessage().getText();
    UserWriteBot userWriteBot = new UserWriteBot();
    userWriteBot.setFirstName(firstName);
    userWriteBot.setLastName(lastName);
    userWriteBot.setButtonName(buttonName);
    return userWriteBot;
  }

  @Override
  public List<UserWriteBot> getAllUsers() {
    List<UserWriteBot> userWriteBotList = new ArrayList<>();
    userVisitRepository.findAll().forEach(userWriteBotList::add);
    return userWriteBotList;
  }
}
