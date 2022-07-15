package training_telegram_bot.demo.service;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public interface DocumentHtmlParserService {

  Document getDocumentFromUrl(String connectionUrl);

  Elements getElementsFromDocument(Document document, String cssQuery);
}
