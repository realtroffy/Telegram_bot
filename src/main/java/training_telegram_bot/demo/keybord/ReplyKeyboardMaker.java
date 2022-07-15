package training_telegram_bot.demo.keybord;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplyKeyboardMaker {

  public ReplyKeyboardMarkup getMainMenuKeyboard() {
    KeyboardRow row1 = new KeyboardRow();
    row1.add("\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98");
    row1.add("\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98");

    KeyboardRow row2 = new KeyboardRow();
    row2.add("❓Гробы_чгк⚰️. Нужно подождать 3-5 секунд");

    List<KeyboardRow> keyboard = new ArrayList<>();
    keyboard.add(row1);
    keyboard.add(row2);

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setKeyboard(keyboard);
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);

    return replyKeyboardMarkup;
  }
}
