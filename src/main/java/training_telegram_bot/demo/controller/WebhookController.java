package training_telegram_bot.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.UserWriteBot;
import training_telegram_bot.demo.service.TelegramFacade;
import training_telegram_bot.demo.service.WebHookService;

@RestController("/")
@AllArgsConstructor
public class WebhookController {

  private final WebHookService webHookService;
  private final TelegramFacade telegramFacade;

  @PostMapping
  public Message onUpdateReceived(@RequestBody Update update) {
    UserWriteBot userWriteBot = webHookService.getUserFromUpdate(update);
    webHookService.saveToDataBase(userWriteBot);
    return telegramFacade.handleUpdate(update);
  }

  @GetMapping
  public ResponseEntity<String> getAnswerIfAppIsAvailable() {
    return ResponseEntity.ok("App works fine");
  }
}
