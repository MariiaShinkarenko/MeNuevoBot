package org.example;


import org.example.configs.LinksConfig;
import org.example.enums.Commands;
import org.example.enums.Zodiac;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Bot extends TelegramLongPollingBot {
    final private String BOT_TOKEN = "6358801713:AAHjLKYc6Hbodkkz3nV_jxiE8SU3IcdNGsM";
    final private String BOT_NAME = "MeNuevoBot";
    private final LinksConfig config = new LinksConfig();
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
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "запусти бота"));
        listofCommands.add(new BotCommand("/info", "бытие бота"));

        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        //Сравниваем текст пользователя с нашими командами, на основе это формируем ответ
        if (textMsg.equals(Commands.START.getCommandType())) {
            response = "Приветствую. Какой прогноз Вас интересует? ";
        } else if (textMsg.equals(Commands.INFO.getCommandType())) {
            response = "Данный гороскоп разработан в учебных целях";
        } else if (isZodiac(textMsg)){
            response = storage.parser(config.getLinkByZodiac(Zodiac.nameToZodiac(textMsg.trim())));
        } else {
            response = "Попробуй еще раз";
        }
        return response;
    }

private boolean isZodiac (String textMsg){
    Set<String> names = new HashSet<>();
    for (Zodiac zodiac:Zodiac.values()) {
        names.add(zodiac.getName());
    }
    return names.contains(textMsg.trim());
}
    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);// подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(true);// скрываем после использования
        replyKeyboardMarkup.setKeyboard(getZodiacKeyBoard());

        return replyKeyboardMarkup;
    }

    private List<KeyboardRow> getZodiacKeyBoard() {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (Zodiac zodiac : Zodiac.values()) {
            buttons.add(newButton(zodiac));
        }
        int fromIndex = 0;
        int toIndex = 4;
        List<KeyboardRow> result = new ArrayList<>();
        while (toIndex <= buttons.size()) {
            result.add(getKeyBoardRow(new ArrayList<>(buttons.subList(fromIndex, toIndex))));
            fromIndex += 4;
            toIndex += 4;
        }

        return result;
    }


    public KeyboardButton newButton(Zodiac zodiac) {
        return new KeyboardButton(zodiac.getName());
    }

    private KeyboardRow getKeyBoardRow(List<KeyboardButton> buttons) {
        KeyboardRow row = new KeyboardRow();
        for (KeyboardButton button : buttons) {
            row.add(button);
        }
        return row;
    }
}




