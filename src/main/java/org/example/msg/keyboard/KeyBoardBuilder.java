package org.example.msg.keyboard;

import org.example.enums.Zodiac;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardBuilder {
    private int buttonsInRaw = 4;
    private List<String> namesButtons = new ArrayList<>();
    private KeyBoardBuilder(){}

    public static KeyBoardBuilder builder (){
        return new KeyBoardBuilder();
    }
    public KeyBoardBuilder setNamesButtons (List<String> namesButtons){
     this.namesButtons.addAll(namesButtons);
     return this;
    }
    public KeyBoardBuilder setButtonsInRaw (int buttonsInRaw){
        this.buttonsInRaw = buttonsInRaw;
        return this;
    }
    public ReplyKeyboard build (){
        if (namesButtons.size() % buttonsInRaw != 0){
            throw new RuntimeException("Количество кнопок не делится без остатка");
        }
        return replyKeyboardMarkup();
    }
   private ReplyKeyboardMarkup replyKeyboardMarkup() {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);// подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(true);// скрываем после использования
        replyKeyboardMarkup.setKeyboard(getKeyBoard());

        return replyKeyboardMarkup;
    }

    private List<KeyboardRow> getKeyBoard() {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (String name : namesButtons) {
            buttons.add(newButton(name));
        }
        int fromIndex = 0;
        int toIndex = buttonsInRaw;
        List<KeyboardRow> result = new ArrayList<>();
        while (toIndex <= buttons.size()) {
            result.add(getKeyBoardRow(new ArrayList<>(buttons.subList(fromIndex, toIndex))));
            fromIndex += buttonsInRaw;
            toIndex += buttonsInRaw;
        }

        return result;
    }


    private KeyboardButton newButton(String buttonName) {
        return new KeyboardButton(buttonName);
    }

    private KeyboardRow getKeyBoardRow(List<KeyboardButton> buttons) {
        KeyboardRow row = new KeyboardRow();
        for (KeyboardButton button : buttons) {
            row.add(button);
        }
        return row;
    }

}
