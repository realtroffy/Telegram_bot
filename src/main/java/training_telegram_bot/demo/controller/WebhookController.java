package training_telegram_bot.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.TelegramBot;
import training_telegram_bot.demo.model.UserWriteBot;
import training_telegram_bot.demo.service.UserVisitService;

@RestController("/")
@AllArgsConstructor
public class WebhookController {

  private final TelegramBot telegramBot;
  private final UserVisitService userVisitService;

  @PostMapping
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    UserWriteBot userWriteBot = userVisitService.getUserFromUpdate(update);
    userVisitService.saveToDataBase(userWriteBot);
    return telegramBot.onWebhookUpdateReceived(update);
  }

  @GetMapping
  public ResponseEntity<String> getAnswerIfAppIsAvailable() {
    return ResponseEntity.ok("App work fine");
  }
}
