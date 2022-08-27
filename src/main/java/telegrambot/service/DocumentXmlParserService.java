package telegrambot.service;

import org.w3c.dom.Node;

public interface DocumentXmlParserService {

  Node getNode(String stringXml, String tag);
}
