package training_telegram_bot.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import training_telegram_bot.demo.service.TelegramFacade;

@Getter
@Setter
@Component
public class TelegramBot extends SpringWebhookBot {

  @Value("${telegrambot.webHookPath}")
  private String botPath;

  @Value("${telegrambot.userName}")
  private String botUsername;

  @Value("${telegrambot.botToken}")
  private String botToken;

  private final TelegramFacade telegramFacade;

  public TelegramBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
    super(setWebhook);
    this.telegramFacade = telegramFacade;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return telegramFacade.handleUpdate(update);
  }
}
