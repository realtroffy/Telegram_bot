package telegrambot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class SetWebhookConfig {

  @Value("${telegrambot.webHookPath}")
  private String botPath;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder().url(botPath).build();
  }
}
