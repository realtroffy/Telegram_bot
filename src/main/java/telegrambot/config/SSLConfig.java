package telegrambot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Configuration
@AllArgsConstructor
public class SSLConfig {

  private final Environment env;

  @PostConstruct
  private void configureSSL() {
    System.setProperty("https.protocols", "TLSv1.3");
    System.setProperty("javax.net.ssl.trustStore", Objects.requireNonNull(env.getProperty("server.ssl.trust-store")));
    System.setProperty(
        "javax.net.ssl.trustStorePassword", Objects.requireNonNull(env.getProperty("server.ssl.trust-store-password")));
  }
}




