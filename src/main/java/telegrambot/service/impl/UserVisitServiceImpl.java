package telegrambot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import telegrambot.model.UserWriteBot;
import telegrambot.service.UserVisitService;
import telegrambot.repository.UserVisitRepository;

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
