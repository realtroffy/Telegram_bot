package telegrambot.service;

import org.springframework.http.ResponseEntity;

public interface WebClientService {

  ResponseEntity<String> getResponseEntity();

}
