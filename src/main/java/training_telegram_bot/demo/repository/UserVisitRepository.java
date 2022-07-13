package training_telegram_bot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import training_telegram_bot.demo.model.User;


public interface UserVisitRepository extends CrudRepository<User, Long> {
}
