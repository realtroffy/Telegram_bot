package telegrambot.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.model.CHGKQuestion;
import telegrambot.model.UserWriteBot;
import telegrambot.service.TelegramFacade;
import telegrambot.service.WebClientService;
import telegrambot.service.WebHookService;

@RestController
@AllArgsConstructor
public class WebhookController {

  private final WebHookService webHookService;
  private final TelegramFacade telegramFacade;
  private final WebClientService webClientService;

  @PostMapping("/update")
  public Message onUpdateReceived(@RequestBody Update update) {
    UserWriteBot userWriteBot = webHookService.getUserFromUpdate(update);
    webHookService.saveToDataBase(userWriteBot);
    return telegramFacade.handleUpdate(update);
  }

  @GetMapping(
      value = "/question",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CHGKQuestion> getQuestion() {
    String response = webClientService.getResponseEntity().getBody();
    CHGKQuestion chgkQuestion = convertStringToObject(response);
    return ResponseEntity.ok(chgkQuestion);
  }

  @SneakyThrows
  private CHGKQuestion convertStringToObject(String response) {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
    return xmlMapper.readValue(response, CHGKQuestion.class);
  }
}
