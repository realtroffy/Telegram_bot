package training_telegram_bot.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import training_telegram_bot.demo.model.UserWriteBot;

public interface UserVisitService {

    Page<UserWriteBot> findAllUserWriteBot(Pageable pageable);

    long countAllUserWriteBot();
}
