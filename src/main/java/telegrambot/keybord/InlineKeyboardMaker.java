package telegrambot.keybord;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "inline", name = "keyboard", havingValue = "on")
public class InlineKeyboardMaker {

  public ReplyKeyboard getInlineKeyboard() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonDate = new InlineKeyboardButton();
    buttonDate.setText("попутка_Минск_Могилев");
    InlineKeyboardButton buttonDescription = new InlineKeyboardButton();
    buttonDescription.setText("попутка_Могилев_Минск");
    InlineKeyboardButton buttonFreq = new InlineKeyboardButton();
    buttonFreq.setText("вопрос_ЧГК");

    buttonDate.setCallbackData("minsk_mogilev");
    buttonDescription.setCallbackData("mogilev_minsk");
    buttonFreq.setCallbackData("question");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
    List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();

    keyboardButtonsRow1.add(buttonDate);
    keyboardButtonsRow2.add(buttonDescription);
    keyboardButtonsRow3.add(buttonFreq);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);
    rowList.add(keyboardButtonsRow2);
    rowList.add(keyboardButtonsRow3);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }
}
