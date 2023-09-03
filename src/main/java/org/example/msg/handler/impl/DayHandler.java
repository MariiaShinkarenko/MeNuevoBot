package org.example.msg.handler.impl;

import org.example.enums.Days;

import org.example.enums.Zodiac;
import org.example.msg.UrlHandlerToday;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class DayHandler implements MessageHandler {
    private UrlHandlerToday urlHandler = new UrlHandlerToday();

    @Override
    public SendMessage handle(Update update) {
        String textMessage = UpdateUtil.getTextMessage(update);
        String answer = getAnswer(textMessage);
        ReplyKeyboard replyKeyboard = KeyBoardBuilder.builder()
                .setButtonsInRaw(4)
                .setNamesButtons(Zodiac.getNamesZodiacs())
                .build();
        return SendMessage.builder()
                .chatId(UpdateUtil.getChatId(update))
                .text(answer)
                .replyMarkup(replyKeyboard)
                .build();

    }

    private String getAnswer(String text) {
        Zodiac.getNamesZodiacs();
        switch (Days.nameToDay(text)) {
            case TODAY:
            case TOMORROW:
                return "Выберите свой знак зодиака";
            default:
                return "Не знаю такой команды: " + text;
        }
    }
}
