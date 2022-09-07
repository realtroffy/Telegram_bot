package telegrambot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import telegrambot.service.WebClientService;

import java.time.Duration;

@Service
public class CHGKWebClientServiceImpl implements WebClientService {

  @Value("${question.url}")
  private String questionUrl;

  private final WebClient webClient;

  @Autowired
  public CHGKWebClientServiceImpl(WebClient webClient) {
    this.webClient = webClient;
  }

  public CHGKWebClientServiceImpl(String questionUrl) {
    this.webClient = WebClient.create(questionUrl);
  }

  @Override
  public ResponseEntity<String> getResponseEntity() {
    return webClient
        .get()
        .uri(questionUrl)
        .retrieve()
        .toEntity(String.class)
        .timeout(Duration.ofMinutes(1))
        .block();
  }
}
