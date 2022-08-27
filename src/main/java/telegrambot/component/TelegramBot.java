package telegrambot.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@Component
public class TelegramBot extends SpringWebhookBot {

  @Value("${telegrambot.webHookPath}")
  private String botPath;

  @Value("${telegrambot.userName}")
  private String botUsername;

  @Value("${TELEGRAMBOT_TOKEN}")
  private String botToken;

  public TelegramBot(SetWebhook setWebhook) {
    super(setWebhook);
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return null;
  }
}
