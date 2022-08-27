package telegrambot.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import telegrambot.model.UserWriteBot;
import telegrambot.repository.UserVisitRepository;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserVisitServiceImplTest {

  @Mock private UserVisitRepository userVisitRepository;

  @Mock private Pageable pageable;

  @Mock private Page<UserWriteBot> userWriteBotPage;

  @InjectMocks private UserVisitServiceImpl userVisitServiceImpl;

  @Test
  void findAllUserWriteBotTest() {
    when(userVisitRepository.findAll(any(Pageable.class))).thenReturn(userWriteBotPage);

    userVisitServiceImpl.findAllUserWriteBot(pageable);

    verify(userVisitRepository).findAll(any(Pageable.class));
  }

  @Test
  void countAllUserWriteBotTest() {
    when(userVisitRepository.count()).thenReturn(1L);

    userVisitServiceImpl.countAllUserWriteBot();

    verify(userVisitRepository).count();
  }
}
