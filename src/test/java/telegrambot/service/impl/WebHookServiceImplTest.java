package telegrambot.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import telegrambot.model.UserWriteBot;
import telegrambot.repository.UserVisitRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class WebHookServiceImplTest {

  @Mock private UserVisitRepository userVisitRepository;
  @Mock private UserWriteBot userWriteBot;

  @InjectMocks private WebHookServiceImpl webHookServiceImpl;

  @Test
  void saveToDataBaseTest() {
    when(userVisitRepository.save(any(UserWriteBot.class))).thenReturn(userWriteBot);

    webHookServiceImpl.saveToDataBase(userWriteBot);

    verify(userVisitRepository).save(any(UserWriteBot.class));
  }

  @Test
  void getUserFromUpdateTest() {
    User user = new User();
    user.setFirstName("Artur");
    user.setLastName("Asiptsou");

    Message message = new Message();
    message.setFrom(user);
    message.setText("button");

    Update update = new Update();
    update.setMessage(message);

    userWriteBot = new UserWriteBot();
    userWriteBot.setId(1L);
    userWriteBot.setFirstName(user.getFirstName());
    userWriteBot.setLastName(user.getLastName());
    userWriteBot.setButtonName(message.getText());

    webHookServiceImpl.getUserFromUpdate(update);

    assertEquals("Artur", userWriteBot.getFirstName());
    assertEquals("Asiptsou", userWriteBot.getLastName());
    assertEquals("button", userWriteBot.getButtonName());
  }
}
