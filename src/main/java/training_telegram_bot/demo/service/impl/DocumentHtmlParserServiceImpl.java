package training_telegram_bot.demo.service.impl;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import training_telegram_bot.demo.service.DocumentHtmlParserService;

@Service
public class DocumentHtmlParserServiceImpl implements DocumentHtmlParserService {

  @Value("${user.agent}")
  private String userAgent;

  @Value("${referrer}")
  private String referrer;

  private final SSLHelper sslHelper;

  public DocumentHtmlParserServiceImpl(SSLHelper sslHelper) {
    this.sslHelper = sslHelper;
  }

  @Override
  @SneakyThrows
  public Document getDocumentFromUrl(String connectionUrl) {
    return sslHelper.getConnection(connectionUrl).get();
  }

  @Override
  public Elements getElementsFromDocument(Document document, String cssQuery) {
    return document.select(cssQuery);
  }
}
