package telegrambot.parser.xmlimpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import telegrambot.error.GetBodyFromStringXmlException;
import telegrambot.parser.XmlParser;
import telegrambot.service.DocumentXmlParserService;
import telegrambot.service.WebClientService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class CHGKXmlParser implements XmlParser {

  public static final String QUESTION = "Question";
  public static final String ANSWER = "Answer";
  public static final String COMMENT = "Comments";
  public static final String ERROR_XML_MESSAGE = "Ошибка парсинга xml";
  public static final String PICTURE_PATTERN = "\\d+\\.(jpg|png|gif|bmp)";
  public static final String PICTURE_URL = "https://db.chgk.info/images/db/";

  private final WebClientService webClientService;
  private final DocumentXmlParserService documentXmlParserService;

  public Map<String, Object> processQuestionButton() {
    Map<String, Object> questionInfo = new HashMap<>();
    ResponseEntity<String> stringQuestionXml = webClientService.getResponseEntity();

    if (stringQuestionXml != null) {

      String responseXml = stringQuestionXml.getBody();
      if (responseXml == null || responseXml.isEmpty()) {
        log.error(ERROR_XML_MESSAGE);
        throw new GetBodyFromStringXmlException(ERROR_XML_MESSAGE);
      }

      String withoutNewLine = responseXml.replace("\n", " ");

      Node question = documentXmlParserService.getNode(withoutNewLine, QUESTION);
      Node answer = documentXmlParserService.getNode(withoutNewLine, ANSWER);
      Node comment = documentXmlParserService.getNode(withoutNewLine, COMMENT);

      String questionString = question.getFirstChild().getNodeValue();
      String answerString = answer.getFirstChild().getNodeValue();
      String commentString;

      questionInfo.put("pictureUrls", getPictureUrlIfPresent(questionString));

      try {
        commentString = comment.getFirstChild().getNodeValue();
      } catch (NullPointerException npe) {
        commentString = "";
      }

      String completeQuestion =
          questionString
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

      questionInfo.put("completeQuestion", completeQuestion);
    }
    return questionInfo;
  }

  private List<String> getPictureUrlIfPresent(String questionString) {
    List<String> pictureUrls = new ArrayList<>();

    Pattern pattern = Pattern.compile(PICTURE_PATTERN);
    Matcher matcher = pattern.matcher(questionString);
    String pictureString;

    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      pictureString = questionString.substring(start, end);
      if (pictureString.length()>0) {
        pictureUrls.add(PICTURE_URL + pictureString);
      }
    }
    return pictureUrls;
  }
}
