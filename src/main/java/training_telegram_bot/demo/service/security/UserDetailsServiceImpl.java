package training_telegram_bot.demo.service.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import training_telegram_bot.demo.model.UserCredential;
import training_telegram_bot.demo.repository.UserCredentialRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserCredentialRepository userCredentialRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserCredential> userCredential =
        userCredentialRepository.findUserCredentialByUsername(username);

    if (userCredential.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return new UserDetailsImpl(userCredential.get());
  }
}
