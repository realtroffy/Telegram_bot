package training_telegram_bot.demo.parser.htmlImpl;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import training_telegram_bot.demo.service.DocumentHtmlParserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KFCHtmlParser {

  public static final String COUPON_NAME_QUERY = "li.coupon-list__item>img[src]";

  private final DocumentHtmlParserService documentHtmlParserService;

  public SendMediaGroup getMessageFromDocument(String urlForParse, Long chatId) {
    Document document = documentHtmlParserService.getDocumentFromUrl(urlForParse);

    Elements coupon =
        documentHtmlParserService.getElementsFromDocument(document, COUPON_NAME_QUERY);
    List<String> strings = coupon.stream().map(e -> e.attr("src")).collect(Collectors.toList());

    SendMediaGroup sendMediaGroup = new SendMediaGroup();
    sendMediaGroup.setChatId(chatId);
    List<InputMedia> inputMediaList = new ArrayList<>();

    for (String element : strings) {
      inputMediaList.add(new InputMediaPhoto(element));
    }
    sendMediaGroup.setMedias(inputMediaList);
    return sendMediaGroup;
  }
}
