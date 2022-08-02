package training_telegram_bot.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import training_telegram_bot.demo.error.GetConnectionException;
import training_telegram_bot.demo.service.DocumentHtmlParserService;

import java.io.IOException;

@Service
@Slf4j
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
  public Document getDocumentFromUrl(String connectionUrl) {
    try {
      return sslHelper.getConnection(connectionUrl).get();
    } catch (IOException e) {
      log.error("Exception while get document from connection", e);
      throw new GetConnectionException("Exception while get document from connection");
    }
  }

  @Override
  public Elements getElementsFromDocument(Document document, String cssQuery) {
    return document.select(cssQuery);
  }
}
