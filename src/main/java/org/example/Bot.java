package org.example;


import org.example.enums.Zodiac;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    final private String BOT_TOKEN = "6358801713:AAHjLKYc6Hbodkkz3nV_jxiE8SU3IcdNGsM";
    final private String BOT_NAME = "MeNuevoBot";
    Storage storage;

    Bot() {
        storage = new Storage();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                // Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                // Добавляем в наше сообщение id чата, а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);
                outMess.setReplyMarkup(replyKeyboardMarkup());

                //Отправка в чат
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public String parseMessage(String textMsg) {
        String response;
        //Сравниваем текст пользователя с нашими командами, на основе это формируем ответ
        if (textMsg.equals("/start")) {
            response = "Приветствую. Какой прогноз Вас интересует? ";
        }

      else if ( textMsg.equals("Овен")) {
                response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/aries.html");
            }
        else if ( textMsg.equals("Телец")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/taurus.html");
        }
        else if ( textMsg.equals("Близнец")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/gemini.html");
        }
        else if ( textMsg.equals("Рак")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/cancer.html");
        }
        else if ( textMsg.equals("Лев")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/leo.html");
        }
       else if ( textMsg.equals("Дева")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/virgo.html");
        }
        else if ( textMsg.equals("Весы")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/libra.html");
        }
        else if ( textMsg.equals("Скорпион")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/scorpio.html");
        }
        else if ( textMsg.equals("Стрелец")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/sagittarius.html");
        }
        else if ( textMsg.equals("Козерог")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/capricorn.html");
        }
        else if ( textMsg.equals("Водолей")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/aquarius.html");
        }
        else if ( textMsg.equals("Рыбы")) {
            response = storage.parser("https://astroscope.ru/horoskop/ejednevniy_goroskop/pisces.html");
        }

        else {
            response = "Попробуй еще раз";
        }
        return response;
    }


    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);// подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(true);// скрываем после использования
        replyKeyboardMarkup.setKeyboard(getZodiacKeyBoard());
        return replyKeyboardMarkup;
    }
    private List<KeyboardRow> getZodiacKeyBoard (){
        List<KeyboardRow> result = new ArrayList<>();
        List<KeyboardButton> buttons = new ArrayList<>();
        for (Zodiac zodiac: Zodiac.values()){
            buttons.add(newButton(zodiac));
        }
        int fromIndex = 0;
        int toIndex = 4;
        while (toIndex<=buttons.size()){
            result.add(getKeyBoardRow(new ArrayList<>(buttons.subList(fromIndex,toIndex))));
            fromIndex+=4;
            toIndex+=4;
        }

        return result;
    }
    public KeyboardButton newButton(Zodiac zodiac){
        return new KeyboardButton(zodiac.getName());
    }
private KeyboardRow getKeyBoardRow (List<KeyboardButton> buttons){
        KeyboardRow row = new KeyboardRow();
        for (KeyboardButton button:buttons){
            row.add(button);
        }
        return row;
}
}



