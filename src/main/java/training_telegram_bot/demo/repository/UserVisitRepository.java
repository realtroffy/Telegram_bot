package training_telegram_bot.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import training_telegram_bot.demo.model.UserWriteBot;

public interface UserVisitRepository extends PagingAndSortingRepository<UserWriteBot, Long> {
}
