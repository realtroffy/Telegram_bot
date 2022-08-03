package training_telegram_bot.demo.service.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Service
public class SSLHelper {

  public Connection getConnection(String url) {
    return Jsoup.connect(url);//.sslSocketFactory(socketFactory());
  }

  private SSLSocketFactory socketFactory() {
    TrustManager[] trustAllCerts =
        new TrustManager[] {
          new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
              /* No need implement */
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                /* No need implement */
            }
          }
        };
    try{
    SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
    return sslContext.getSocketFactory();
  } catch (Exception e){
        throw new RuntimeException(e);
    }
  }
}







