package telegrambot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login2")
  public String loginPage() {
    return "login2";
  }
}
