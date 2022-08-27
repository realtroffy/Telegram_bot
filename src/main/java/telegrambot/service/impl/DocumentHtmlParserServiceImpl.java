package telegrambot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import telegrambot.error.GetConnectionException;
import telegrambot.service.DocumentHtmlParserService;

import java.io.IOException;

@Service
@Slf4j
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
      log.error("Exception while get document from connection", e);
      throw new GetConnectionException("Exception while get document from connection");
    }
  }

  @Override
  public Elements getElementsFromDocument(Document document, String cssQuery) {
    return document.select(cssQuery);
  }
}
