package telegrambot.parser.xmlimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;
import telegrambot.error.CreateObjectFromXmlSourceException;
import telegrambot.error.GetBodyFromStringXmlException;
import telegrambot.model.CHGKQuestion;
import telegrambot.parser.XmlParser;
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

  public static final String ERROR_XML_MESSAGE = "Error parsing xml";
  public static final String PICTURE_PATTERN = "\\d+\\.(jpg|png|gif|bmp)";
  public static final String PICTURE_URL = "https://db.chgk.info/images/db/";

  private final WebClientService webClientService;
  private MappingJackson2XmlHttpMessageConverter xmlConverter;

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
      CHGKQuestion chgkQuestion = convertStringXmlToCHGKQuestion(withoutNewLine);
      questionInfo.put("pictureUrls", getPictureUrlIfPresent(chgkQuestion.getQuestion()));

      String completeQuestion =
          chgkQuestion.getQuestion()
              + "\n"
              + "Ответ: "
              + "<tg-spoiler>"
              + "<strong>"
              + chgkQuestion.getAnswer()
              + "</strong>"
              + "\n"
              + "<em>"
              + chgkQuestion.getComment()
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
      if (pictureString.length() > 0) {
        pictureUrls.add(PICTURE_URL + pictureString);
      }
    }
    return pictureUrls;
  }

  private CHGKQuestion convertStringXmlToCHGKQuestion(String responseWithoutNewLine) {
    ObjectMapper xmlMapper = xmlConverter.getObjectMapper();
    xmlMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
    CHGKQuestion chgkQuestion;
    try {
      chgkQuestion = xmlMapper.readValue(responseWithoutNewLine, CHGKQuestion.class);
    } catch (JsonProcessingException e) {
      throw new CreateObjectFromXmlSourceException("Couldn't read xml source");
    }
    return chgkQuestion;
  }
}
