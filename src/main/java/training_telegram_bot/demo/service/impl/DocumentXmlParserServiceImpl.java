package training_telegram_bot.demo.service.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import training_telegram_bot.demo.service.DocumentXmlParserService;

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

    @SneakyThrows
    private Document createDocumentFromString(String stringXml) {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(stringXml)));
    }
}
