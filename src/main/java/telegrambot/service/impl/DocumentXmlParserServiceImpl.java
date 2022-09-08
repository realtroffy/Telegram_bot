package telegrambot.service.impl;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import telegrambot.service.DocumentXmlParserService;
import telegrambot.error.CreateObjectFromXmlSourceException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

@Service
public class DocumentXmlParserServiceImpl implements DocumentXmlParserService {

  @Override
  public Node getNode(String stringXml, String tag) {
    Document document = createDocumentFromString(stringXml);
    NodeList questionElements = document.getDocumentElement().getElementsByTagName(tag);
    return questionElements.item(0);
  }

  private Document createDocumentFromString(String stringXml) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(new InputSource(new StringReader(stringXml)));
    } catch (Exception e) {
      throw new CreateObjectFromXmlSourceException("Exception while create connection or parse XML");
    }
  }
}
