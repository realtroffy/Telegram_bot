package telegrambot.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.error.TelegramExecuteMessageException;
import telegrambot.keybord.ReplyKeyboardMaker;
import telegrambot.parser.htmlimpl.GoCarHtmlParser;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MessageHandler {

  public static final String MINSK_MOGILEV_URL =
      "https://www.gocar.by/Trip/SearchResult?fromName=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA&fromLocation=53.9%7C27.5666667&fromCountry=BY&toName=%D0%9C%D0%BE%D0%B3%D0%B8%D0%BB%D0%B5%D0%B2%20&toLocation=53.9%7C30.3333333&toCountry=BY";
  public static final String MOGILEV_MINSK_URL =
      "https://www.gocar.by/Trip/SearchResult?fromName=%D0%9C%D0%BE%D0%B3%D0%B8%D0%BB%D0%B5%D0%B2%20&fromLocation=53.9%7C30.3333333&fromCountry=BY&toName=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA&toLocation=53.9%7C27.5666667&toCountry=BY";
  public static final String MINSK_MOGILEV_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98";
  public static final String MOGILEV_MINSK_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98";
  public static final String QUESTION_BUTTON_MESSAGE = "❓Гробы_чгк⚰️. Нужно подождать 3-5 секунд";
  public static final String KFC_BUTTON_MESSAGE = "KFC купоны";
  public static final String KFC_URL_PROMO = "https://www.kfc.by/promo/182";
  public static final String DEFAULT_BOT_MESSAGE =
      "Пожалуйста, воспользуйтесь кнопками для ввода команд";
  public static final String KEY_QUESTION_COMPLETE = "completeQuestion";
  public static final String KEY_PICTURE_QUESTION_URLS = "pictureUrls";

  private final ReplyKeyboardMaker replyKeyboardMaker;
  private final GoCarHtmlParser goCarHtmlParser;
  private final CHGKXmlParser chgkXmlParser;
  private final KFCHtmlParser kfcHtmlParser;
  private final TelegramBot telegramBot;

  @SuppressWarnings("unchecked")
  public Message process(Message message) {

    Long chatId = message.getChatId();
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);

    String inputMessage = message.getText();
    sendMessage.disableWebPagePreview();
    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());

    switch (inputMessage) {
      case "/start":
        sendMessage.setText("Hellova, " + message.getFrom().getFirstName());
        try {
          telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
          log.error("Exception during execute /start in MessageHandler", e);
          throw new TelegramExecuteMessageException(
              "Error during execute /start in MessageHandler");
        }
        break;
      case MINSK_MOGILEV_BUTTON_MESSAGE:
        sendMessage.setText(goCarHtmlParser.getMessageFromDocument(MINSK_MOGILEV_URL));
        try {
          telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
          log.error("Exception during execute MINSK_MOGILEV_BUTTON_MESSAGE in MessageHandler", e);
          throw new TelegramExecuteMessageException(
              "Error during execute MINSK_MOGILEV_BUTTON_MESSAGE in MessageHandler");
        }
        break;
      case MOGILEV_MINSK_BUTTON_MESSAGE:
        sendMessage.setText(goCarHtmlParser.getMessageFromDocument(MOGILEV_MINSK_URL));
        try {
          telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
          log.error("Exception during execute MOGILEV_MINSK_BUTTON_MESSAGE in MessageHandler", e);
          throw new TelegramExecuteMessageException(
              "Error during execute MOGILEV_MINSK_BUTTON_MESSAGE in MessageHandler");
        }
        break;
      case QUESTION_BUTTON_MESSAGE:
        Map<String, Object> questionMap = chgkXmlParser.processQuestionButton();
        String messageFromXml = (String) questionMap.get(KEY_QUESTION_COMPLETE);
        List<String> chgkPictureUrls = (List<String>) questionMap.get(KEY_PICTURE_QUESTION_URLS);
        sendMessage.setText(messageFromXml);
        try {
          telegramBot.execute(sendMessage);
          for (String pictureUrl : chgkPictureUrls) {
            executePhoto(chatId, pictureUrl);
          }
        } catch (TelegramApiException e) {
          log.error("Exception during execute QUESTION_BUTTON_MESSAGE in MessageHandler", e);
          throw new TelegramExecuteMessageException(
              "Error during execute QUESTION_BUTTON_MESSAGE in MessageHandler");
        }
        break;
      case KFC_BUTTON_MESSAGE:
        List<String> kfcPictureUrls = kfcHtmlParser.getMessageFromDocument(KFC_URL_PROMO);
        for (String pictureUrl : kfcPictureUrls) {
          executePhoto(chatId, pictureUrl);
        }
        break;
      default:
        sendMessage.setText(DEFAULT_BOT_MESSAGE);
        try {
          telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
          log.error("Exception during execute DEFAULT_BOT_MESSAGE in MessageHandler", e);
          throw new TelegramExecuteMessageException(
              "Error during execute DEFAULT_BOT_MESSAGE in MessageHandler");
        }
    }
    return null;
  }

  private void executePhoto(Long chatId, String pictureUrl) {
    SendPhoto sendPhoto = new SendPhoto();
    sendPhoto.setChatId(chatId);
    sendPhoto.setPhoto(new InputFile(pictureUrl));
    try {
      telegramBot.execute(sendPhoto);
    } catch (TelegramApiException e) {
      log.error("Exception during execute KFC_BUTTON_MESSAGE in MessageHandler", e);
      throw new TelegramExecuteMessageException(
          "Error during execute KFC_BUTTON_MESSAGE in MessageHandler");
    }
  }
}
