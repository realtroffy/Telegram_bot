package training_telegram_bot.demo.parser.htmlImpl;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import training_telegram_bot.demo.parser.HtmlParser;
import training_telegram_bot.demo.service.DocumentHtmlParserService;

@Service
@AllArgsConstructor
public class KFCHtmlParser implements HtmlParser {

  public static final String COUPON_NAME_QUERY = "li.coupon-list_item";

  private final DocumentHtmlParserService documentHtmlParserService;

  @Override
  public String getMessageFromDocument(String urlForParse) {
    Document document = documentHtmlParserService.getDocumentFromUrl(urlForParse);

    Elements coupon =
        documentHtmlParserService.getElementsFromDocument(document, COUPON_NAME_QUERY);

    StringBuilder couponMessage = new StringBuilder();
    for (org.jsoup.nodes.Element element : coupon) {
      couponMessage.append(element.text());
    }
    return couponMessage.toString();
  }
}
