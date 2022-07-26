package training_telegram_bot.demo.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import training_telegram_bot.demo.service.DocumentHtmlParserService;

import java.io.IOException;

@Service
public class DocumentHtmlParserServiceImpl implements DocumentHtmlParserService {

  @Value("${user.agent}")
  private String userAgent;

  @Value("${referrer}")
  private String referrer;

  @Override
  public Document getDocumentFromUrl(String connectionUrl) {
    try {
      return Jsoup.connect(connectionUrl).get();
    } catch (IOException e) {
      throw new RuntimeException("Can't open connection");
    }
  }

  @Override
  public Elements getElementsFromDocument(Document document, String cssQuery) {
    return document.select(cssQuery);
  }
}
