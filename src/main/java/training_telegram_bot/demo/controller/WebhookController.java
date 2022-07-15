package training_telegram_bot.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.TelegramBot;
import training_telegram_bot.demo.model.User;
import training_telegram_bot.demo.service.UserVisitService;

@RestController
public class WebhookController {

  private final TelegramBot telegramBot;
  private final UserVisitService userVisitService;

  public WebhookController(TelegramBot telegramBot, UserVisitService userVisitService) {
    this.telegramBot = telegramBot;
    this.userVisitService = userVisitService;
  }

  @PostMapping("/")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    User user = userVisitService.getUserFromUpdate(update);
    userVisitService.saveToDataBase(user);
    return telegramBot.onWebhookUpdateReceived(update);
  }

  @GetMapping("/")
  public ResponseEntity<String> getAnswerIfAppIsAvailable() {
    return ResponseEntity.ok("App work fine");
  }
}
