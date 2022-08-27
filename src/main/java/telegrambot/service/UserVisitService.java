package telegrambot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import telegrambot.model.UserWriteBot;

public interface UserVisitService {

    Page<UserWriteBot> findAllUserWriteBot(Pageable pageable);

    long countAllUserWriteBot();
}
