package org.example.msg.handler.impl;

import org.example.cache.EventCache;
import org.example.entity.UserEvent;
import org.example.enums.Day;
import org.example.enums.Zodiac;
import org.example.msg.UrlHandler;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class DayHandler implements MessageHandler {
    private UrlHandler urlHandler = new UrlHandler();
    private EventCache eventCache = EventCache.getInstance();

/**
 * 1. Создать класс для запоминания выбранного дня.
 * 2. Для кеширования выбранного дня будем использовать Queue.
 * 3. Создание сущности, которая будет храниться в очереди.
* */

    @Override
    public SendMessage handle(Update update) {
        String textMessage = UpdateUtil.getTextMessage(update);
        String answer = getAnswer(textMessage);
        updateCache(update, textMessage);
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
        switch (Day.nameToDay(text)) {
            case TODAY:
            case TOMORROW:
                return "Выберите свой знак зодиака";
            default:
                return "Не знаю такой команды: " + text;
        }
    }
    private void updateCache(Update update, String text){
        UserEvent userEvent = new UserEvent(update, update.getMessage().getFrom().getUserName(), text);
        eventCache.addEvent(userEvent);

    }
}
