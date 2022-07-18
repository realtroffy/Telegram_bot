package training_telegram_bot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import training_telegram_bot.demo.model.UserWriteBot;

public interface UserVisitRepository extends CrudRepository<UserWriteBot, Long> {}
