package training_telegram_bot.demo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import training_telegram_bot.demo.model.UserWriteBot;
import training_telegram_bot.demo.repository.UserVisitRepository;
import training_telegram_bot.demo.service.UserVisitService;

@Service
@AllArgsConstructor
public class UserVisitServiceImpl implements UserVisitService {

  private final UserVisitRepository userVisitRepository;

  @Override
  public Page<UserWriteBot> findAllUserWriteBot(Pageable pageable) {
    return userVisitRepository.findAll(pageable);
  }

  @Override
  public long countAllUserWriteBot() {
    return userVisitRepository.count();
  }
}
