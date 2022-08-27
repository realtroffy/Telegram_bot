package telegrambot.parser.htmlimpl;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import telegrambot.service.DocumentHtmlParserService;

@Service
@AllArgsConstructor
public class GoCarHtmlParser {

  public static final String DATE_NAME_QUERY = "div.trip_block > time.time";
  public static final String PRICE_NAME_QUERY = "div.price.price-green";
  public static final String EMPTY_SEAT_NAME_QUERY = "div.availability > strong";
  public static final String LINK_NAME_QUERY = "li[class=\"\"] > a[class=\"\"]";
  public static final String DRIVER_NAME_QUERY = "div.h5 > strong";
  public static final String NOT_FOUND_TRIP_MESSAGE =
      "Поездок не найдено. Возможно появятся позднее\uD83D\uDE09";

  private final DocumentHtmlParserService documentHtmlParserService;

  public String getMessageFromDocument(String urlForParse) {
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
