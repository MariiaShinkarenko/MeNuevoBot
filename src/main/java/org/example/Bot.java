package org.example;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;


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

        // Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        // Добавляем одну кнопку с текстом
        keyboardRow.add(new KeyboardButton("Овен"));
        keyboardRow.add(new KeyboardButton("Телец"));
        keyboardRow.add(new KeyboardButton("Близнец"));
        keyboardRow.add(new KeyboardButton("Рак"));
        keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        keyboardRow.add(new KeyboardButton("Лев"));
        keyboardRow.add(new KeyboardButton("Дева"));
        keyboardRow.add(new KeyboardButton("Весы"));
        keyboardRow.add(new KeyboardButton("Скорпион"));
        keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        keyboardRow.add(new KeyboardButton("Стрелец"));
        keyboardRow.add(new KeyboardButton("Козерог"));
        keyboardRow.add(new KeyboardButton("Водолей"));
        keyboardRow.add(new KeyboardButton("Рыбы"));
        // добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}



