package training_telegram_bot.demo.parser.xmlImpl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import training_telegram_bot.demo.parser.XmlParser;
import training_telegram_bot.demo.service.DocumentXmlParserService;
import training_telegram_bot.demo.service.WebClientService;

@Service
@AllArgsConstructor
public class CHGKXmlParser implements XmlParser {

  public static final String QUESTION = "Question";
  public static final String ANSWER = "Answer";
  public static final String COMMENT = "Comments";
  public static final String ERROR_XML_MESSAGE = "Ошибка парсинга xml";

  private final WebClientService webClientService;
  private final DocumentXmlParserService documentXmlParserService;

  public String processQuestionButton() {
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
}
