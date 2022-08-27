package telegrambot.repository;

import org.springframework.data.repository.CrudRepository;
import telegrambot.model.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends CrudRepository<UserCredential, Long> {

    Optional<UserCredential> findUserCredentialByUsername(String username);
}
