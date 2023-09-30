package org.example.msg.handler.impl;

import org.example.cache.EventCache;
import org.example.entity.UserEvent;
import org.example.enums.Day;
import org.example.enums.Menu;
import org.example.enums.Zodiac;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class MenuHandler implements MessageHandler {
    private EventCache eventCache = EventCache.getInstance();

    @Override
    public SendMessage handle(Update update) {
        String textMessage = UpdateUtil.getTextMessage(update);
        Menu menu = Menu.nameToMenu(textMessage);
        switch (menu) {
            case HOROSCOPE:
                return buildHoroscopeSendMessage(update);
            case CHARACTERISTIC:
                return buildCharacteristicSendMessage(update, textMessage);
        }
        throw new RuntimeException("Не выбран один из предложенных вариантов");
    }

    private SendMessage buildHoroscopeSendMessage(Update update) {
        ReplyKeyboard replyKeyboard = KeyBoardBuilder.builder()
                .setButtonsInRaw(2)
                .setNamesButtons(Day.getNamesDays())
                .build();
        return SendMessage.builder()
                .chatId(UpdateUtil.getChatId(update))
                .text("Выбери день")
                .replyMarkup(replyKeyboard)
                .build();
    }

    private SendMessage buildCharacteristicSendMessage(Update update, String text) {
        updateCache(update, text);
        ReplyKeyboard replyKeyboard = KeyBoardBuilder.builder()
                .setNamesButtons(Zodiac.getNamesZodiacs())
                .setButtonsInRaw(4)
                .build();

        return SendMessage.builder()
                .replyMarkup(replyKeyboard)
                .chatId(UpdateUtil.getChatId(update))
                .text("Выбери знак зодиака")
                .build();
    }

    private void updateCache(Update update, String text) {
        UserEvent userEvent = new UserEvent(update, update.getMessage().getFrom().getUserName(), text);
        eventCache.addEvent(userEvent);

    }
}
