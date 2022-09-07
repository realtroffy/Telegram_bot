package telegrambot.parser.xmlimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import telegrambot.service.WebClientService;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CHGKConverter {

    private final WebClientService webClientService;

    public String getQuestionFromUrl(){
        return Objects.requireNonNull(webClientService.getQuestion().getBody());
    }
}
