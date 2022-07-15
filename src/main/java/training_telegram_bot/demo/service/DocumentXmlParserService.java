package training_telegram_bot.demo.service;

import org.w3c.dom.Node;

public interface DocumentXmlParserService {

  Node getNode(String stringXml, String tag);
}
