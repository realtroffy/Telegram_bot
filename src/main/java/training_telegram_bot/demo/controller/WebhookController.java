package training_telegram_bot.demo.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import training_telegram_bot.demo.model.MemoryStats;
import training_telegram_bot.demo.model.UserWriteBot;
import training_telegram_bot.demo.service.TelegramFacade;
import training_telegram_bot.demo.service.UserVisitService;

@RestController("/")
@AllArgsConstructor
public class WebhookController {

  private final UserVisitService userVisitService;
  private final TelegramFacade telegramFacade;

  @PostMapping
  @SneakyThrows
  public Message onUpdateReceived(@RequestBody Update update) {
    UserWriteBot userWriteBot = userVisitService.getUserFromUpdate(update);
    userVisitService.saveToDataBase(userWriteBot);
    return telegramFacade.handleUpdate(update);
  }

  @GetMapping
  public ResponseEntity<String> getAnswerIfAppIsAvailable() {
    return ResponseEntity.ok("App work fine");
  }

  @GetMapping("memory")
  public MemoryStats getMemoryStatistics() {
    MemoryStats stats = new MemoryStats();
    stats.setHeapSize(Runtime.getRuntime().totalMemory());
    stats.setHeapMaxSize(Runtime.getRuntime().maxMemory());
    stats.setHeapFreeSize(Runtime.getRuntime().freeMemory());
    return stats;
  }
}
