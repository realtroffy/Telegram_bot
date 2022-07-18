package training_telegram_bot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import training_telegram_bot.demo.model.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends CrudRepository<UserCredential, Long> {

    Optional<UserCredential> findUserCredentialByUsername(String username);
}
