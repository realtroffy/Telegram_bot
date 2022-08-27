package telegrambot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import telegrambot.model.UserWriteBot;

public interface UserVisitRepository extends PagingAndSortingRepository<UserWriteBot, Long> {
}
