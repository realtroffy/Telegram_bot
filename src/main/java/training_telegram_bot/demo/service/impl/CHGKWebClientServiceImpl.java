package training_telegram_bot.demo.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import training_telegram_bot.demo.service.WebClientService;

import javax.annotation.PostConstruct;

@Service
public class CHGKWebClientServiceImpl implements WebClientService {

  @Value("${question.url}")
  private String questionUrl;

  private final WebClient webClient;

  public CHGKWebClientServiceImpl(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public ResponseEntity<String> getResponseEntity() {
    return webClient.get().uri(questionUrl).retrieve().toEntity(String.class).block();
  }
}
