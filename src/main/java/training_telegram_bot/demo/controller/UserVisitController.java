package training_telegram_bot.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import training_telegram_bot.demo.repository.UserVisitRepository;

@Controller
@AllArgsConstructor
public class UserVisitController {

    private final UserVisitRepository userVisitRepository;

    @GetMapping("/users")
    public String getAllMessagesFromUsers(Model model, Pageable pageable) {
        model.addAttribute("users", userVisitRepository.findAll(pageable));
        return "users";
    }
}
