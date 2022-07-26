package training_telegram_bot.demo.handlers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import training_telegram_bot.demo.keybord.ReplyKeyboardMaker;
import training_telegram_bot.demo.parser.htmlImpl.GoCarHtmlParser;
import training_telegram_bot.demo.parser.htmlImpl.KFCHtmlParser;
import training_telegram_bot.demo.parser.xmlImpl.CHGKXmlParser;

@Service
@AllArgsConstructor
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
  public static final String KFC_URL_PROMO =
      "https://www.kfc.by/promo/182";
  public static final String DEFAULT_BOT_MESSAGE =
      "Пожалуйста, воспользуйтесь кнопками для ввода команд";

  private final ReplyKeyboardMaker replyKeyboardMaker;
  private final GoCarHtmlParser goCarHtmlParser;
  private final CHGKXmlParser chgkXmlParser;
  private final KFCHtmlParser kfcHtmlParser;

  @SneakyThrows
  public BotApiMethod<Message> process(Message message) {

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
        sendMessage.setText(goCarHtmlParser.getMessageFromDocument(MINSK_MOGILEV_URL));
        break;
      case MOGILEV_MINSK_BUTTON_MESSAGE:
        sendMessage.setText(goCarHtmlParser.getMessageFromDocument(MOGILEV_MINSK_URL));
        break;
      case QUESTION_BUTTON_MESSAGE:
        String messageFromXml = chgkXmlParser.processQuestionButton();
        sendMessage.setText(messageFromXml);
        break;
      /*case KFC_BUTTON_MESSAGE:
        sendMessage.setText(kfcHtmlParser.getMessageFromDocument(KFC_URL_PROMO));
        break;*/
      default:
        sendMessage.setText(DEFAULT_BOT_MESSAGE);
    }
    return sendMessage;
  }
}
