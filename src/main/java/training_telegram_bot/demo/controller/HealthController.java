package training_telegram_bot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {

  @GetMapping("/health")
  public String healthPage() {
    return "health";
  }
}
