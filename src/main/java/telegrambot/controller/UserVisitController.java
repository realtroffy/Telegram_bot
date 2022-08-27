package telegrambot.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import telegrambot.service.UserVisitService;

@Controller
@AllArgsConstructor
public class UserVisitController {

  public static final int DEFAULT_COUNT_ENTITY_ON_PAGE = 20;

  private final UserVisitService userVisitService;

  @GetMapping("/users")
  public String getAllMessagesFromUsers(Model model, Pageable pageable) {
    model.addAttribute("users", userVisitService.findAllUserWriteBot(pageable));
    model.addAttribute(
        "lastPage", userVisitService.countAllUserWriteBot() / DEFAULT_COUNT_ENTITY_ON_PAGE);
    return "users";
  }
}
