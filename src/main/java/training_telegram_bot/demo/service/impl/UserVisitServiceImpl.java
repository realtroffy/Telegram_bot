package training_telegram_bot.demo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.User;
import training_telegram_bot.demo.repository.UserVisitRepository;
import training_telegram_bot.demo.service.UserVisitService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserVisitServiceImpl implements UserVisitService {

    private final UserVisitRepository userVisitRepository;

    @Override
    public void saveToDataBase(User user) {
        userVisitRepository.save(user);
    }

    @Override
    public User getUserFromUpdate(Update update) {
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();
        LocalDateTime messageDate = LocalDateTime.now();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMessageDate(messageDate);
        return user;
    }
}
