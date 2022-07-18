package training_telegram_bot.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import training_telegram_bot.demo.model.UserWriteBot;
import training_telegram_bot.demo.service.UserVisitService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserVisitController {

    private final UserVisitService userVisitService;

    @GetMapping("/users")
    public String getAllMessagesFromUsers(Model model) {
        List<UserWriteBot> users = userVisitService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
