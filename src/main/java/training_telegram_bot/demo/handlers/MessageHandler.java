package training_telegram_bot.demo.handlers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.w3c.dom.Node;
import training_telegram_bot.demo.keybord.ReplyKeyboardMaker;
import training_telegram_bot.demo.service.DocumentHtmlParserService;
import training_telegram_bot.demo.service.DocumentXmlParserService;
import training_telegram_bot.demo.service.WebClientService;

@Service
public class MessageHandler {

  private static final String QUESTION = "Question";
  private static final String ANSWER = "Answer";
  private static final String COMMENT = "Comments";
  private static final String MINSK_MOGILEV_URL =
      "https://www.gocar.by/Trip/SearchResult?fromName=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA&fromLocation=53.9%7C27.5666667&fromCountry=BY&toName=%D0%9C%D0%BE%D0%B3%D0%B8%D0%BB%D0%B5%D0%B2%20&toLocation=53.9%7C30.3333333&toCountry=BY";
  private static final String MOGILEV_MINSK_URL =
      "https://www.gocar.by/Trip/SearchResult?fromName=%D0%9C%D0%BE%D0%B3%D0%B8%D0%BB%D0%B5%D0%B2%20&fromLocation=53.9%7C30.3333333&fromCountry=BY&toName=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA&toLocation=53.9%7C27.5666667&toCountry=BY";
  private static final String DATE_NAME_QUERY = "div.trip_block > time.time";
  private static final String PRICE_NAME_QUERY = "div.price.price-green";
  private static final String EMPTY_SEAT_NAME_QUERY = "div.availability > strong";
  private static final String LINK_NAME_QUERY = "li[class=\"\"] > a[class=\"\"]";
  private static final String DRIVER_NAME_QUERY = "div.h5 > strong";
  private static final String ERROR_XML_MESSAGE = "Ошибка парсинга xml";
  private static final String MINSK_MOGILEV_BUTTON_MESSAGE = "\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98";
  private static final String MOGILEV_MINSK_BUTTON_MESSAGE = "\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98";
  private static final String QUESTION_BUTTON_MESSAGE = "❓Гробы_чгк⚰️. Нужно подождать 3-5 секунд";
  private static final String NOT_FOUND_TRIP_MESSAGE = "Поездок не найдено. Возможно появятся позднее\uD83D\uDE09";

  private final ReplyKeyboardMaker replyKeyboardMaker;
  private final WebClientService webClientService;
  private final DocumentXmlParserService documentXmlParserService;
  private final DocumentHtmlParserService documentHtmlParserService;

  public MessageHandler(
      ReplyKeyboardMaker replyKeyboardMaker,
      WebClientService webClientService,
      DocumentXmlParserService documentXmlParserService,
      DocumentHtmlParserService documentHtmlParserService) {
    this.replyKeyboardMaker = replyKeyboardMaker;
    this.webClientService = webClientService;
    this.documentXmlParserService = documentXmlParserService;
    this.documentHtmlParserService = documentHtmlParserService;
  }

  public BotApiMethod<?> process(Message message) {
    String chatId = String.valueOf(message.getChatId());
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);

    String inputMessage = message.getText();

    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());

    switch (inputMessage) {
      case "/start":
        sendMessage.setText("Hellova, " + message.getFrom().getFirstName());
        break;
      case MINSK_MOGILEV_BUTTON_MESSAGE:
        sendMessage.setText(getMessageFromDocument(MINSK_MOGILEV_URL));
        break;
      case MOGILEV_MINSK_BUTTON_MESSAGE:
        sendMessage.setText(getMessageFromDocument(MOGILEV_MINSK_URL));
        break;
      case QUESTION_BUTTON_MESSAGE:
        String messageFromXml = processQuestionButton();
        sendMessage.setText(messageFromXml);
        break;
    }
    return sendMessage;
  }

  private String processQuestionButton() {
    ResponseEntity<String> stringQuestionXml = webClientService.getResponseEntity();

    if (stringQuestionXml != null) {

      String responseXml = stringQuestionXml.getBody();
      if (responseXml == null || responseXml.isEmpty()) {
        return ERROR_XML_MESSAGE;
      }

      String withoutNewLine = responseXml.replace("\n", " ");

      Node question = documentXmlParserService.getNode(withoutNewLine, QUESTION);
      Node answer = documentXmlParserService.getNode(withoutNewLine, ANSWER);
      Node comment = documentXmlParserService.getNode(withoutNewLine, COMMENT);

      String questionString = question.getFirstChild().getNodeValue();
      String answerString = answer.getFirstChild().getNodeValue();
      String commentString;

      try {
        commentString = comment.getFirstChild().getNodeValue();
      } catch (NullPointerException npe) {
        commentString = "";
      }

      return questionString
          + "\n"
          + "Ответ: "
          + "<tg-spoiler>"
          + "<strong>"
          + answerString
          + "</strong>"
          + "\n"
          + "<em>"
          + commentString
          + "</em>"
          + "</tg-spoiler>";

    } else {
      return ERROR_XML_MESSAGE;
    }
  }

  private String getMessageFromDocument(String urlForParse) {
    Document document = documentHtmlParserService.getDocumentFromUrl(urlForParse);

    Elements date = documentHtmlParserService.getElementsFromDocument(document, DATE_NAME_QUERY);
    Elements price = documentHtmlParserService.getElementsFromDocument(document, PRICE_NAME_QUERY);
    Elements emptySeat =
        documentHtmlParserService.getElementsFromDocument(document, EMPTY_SEAT_NAME_QUERY);
    Elements tripLink =
        documentHtmlParserService.getElementsFromDocument(document, LINK_NAME_QUERY);
    Elements name = documentHtmlParserService.getElementsFromDocument(document, DRIVER_NAME_QUERY);

    StringBuilder tripMessage = new StringBuilder();

    for (int i = 0; i < price.size(); i++) {
      tripMessage
          .append("<strong>Цена:</strong> <ins>")
          .append(price.get(i).text())
          .append("</ins>, <strong>Водитель:</strong> <ins>")
          .append(name.get(i).text())
          .append("</ins>, <strong>Дата поездки:</strong> <ins>")
          .append(date.get(i).text())
          .append("</ins>, <strong>Свободных мест:</strong> <ins>")
          .append(emptySeat.get(i).text())
          .append("</ins>, <strong>Ссылка:</strong> https://www.gocar.by")
          .append(tripLink.get(i).attr("href"))
          .append("\n");
    }
    if (tripMessage.toString().isEmpty()) {
      tripMessage.append(NOT_FOUND_TRIP_MESSAGE);
    }
    return tripMessage.toString();
  }
}
