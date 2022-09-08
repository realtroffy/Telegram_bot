package telegrambot.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlMapperConfig {

  @Bean
  public XmlMapper create() {
    return new XmlMapper();
  }
}
