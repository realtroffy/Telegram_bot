package telegrambot.service.impl;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CHGKWebClientServiceImplIT {

  public static MockWebServer mockBackEnd;

  private CHGKWebClientServiceImpl chgkWebClientServiceImpl;

  @BeforeAll
  public static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  public static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @BeforeEach
  public void initialize() {
    chgkWebClientServiceImpl = new CHGKWebClientServiceImpl(mockBackEnd.url("/").toString());
  }

  @Test
  void getResponseEntityTest() {
    String entityAnswer = "Some string";
    mockBackEnd.enqueue(new MockResponse().setBody(entityAnswer));

    ResponseEntity<String> responseEntity = chgkWebClientServiceImpl.getResponseEntity();

    assertEquals("Some string", responseEntity.getBody());
  }
}
