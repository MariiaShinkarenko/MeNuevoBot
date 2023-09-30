package org.example.msg.handler.impl;

import org.example.cache.EventCache;
import org.example.configs.CharacteristicConfig;
import org.example.configs.LinksTodayConfig;
import org.example.configs.LinksTomorrowConfig;
import org.example.entity.UserEvent;
import org.example.enums.Day;
import org.example.enums.Menu;
import org.example.enums.Zodiac;
import org.example.msg.CharacteristicUrlHandler;
import org.example.msg.UrlHandler;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.MessageUtil;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ZodiacHandler implements MessageHandler {
    private UrlHandler urlHandler = new UrlHandler();
    private CharacteristicUrlHandler characteristicUrlHandler = new CharacteristicUrlHandler();
    private CharacteristicConfig characteristicConfig = new CharacteristicConfig();


    private LinksTodayConfig linksTodayConfig = new LinksTodayConfig();
    private LinksTomorrowConfig linksTomorrowConfig = new LinksTomorrowConfig();
    private EventCache eventCache = EventCache.getInstance();


    @Override
    public SendMessage handle(Update update) {
        String textMessage = UpdateUtil.getTextMessage(update);
        Zodiac zodiac = Zodiac.nameToZodiac(textMessage);
        String answer = getAnswer(zodiac, eventCache.getLastEvent());
        ReplyKeyboard replyKeyboard = KeyBoardBuilder.builder()
                .setButtonsInRaw(2)
                .setNamesButtons(Menu.getNamesMenu())
                .build();
        return SendMessage.builder()
                .chatId(UpdateUtil.getChatId(update))
                .text(answer)
                .replyMarkup(replyKeyboard)
                .build();

    }

    private String getAnswer(Zodiac zodiac, UserEvent userEvent) {
        String text = userEvent.getText();
        if (MessageUtil.isDay(text)) {
            String urlZodiac = getUrlDependingOnDay(zodiac, Day.nameToDay(text));
            return urlHandler.getHoroscopeFromUrl(urlZodiac);
        } else if (MessageUtil.isMenu(text)) {
            String urlCharacteristic = characteristicConfig.getLinkByCharacteristic(zodiac);
            return urlHandler.getCharacteristicFromUrl(urlCharacteristic);

        }
        throw new RuntimeException("Сюда не должно было попасть");
    }

    private String getUrlDependingOnDay(Zodiac zodiac, Day day) {
        if (day == Day.TODAY) {
            return linksTodayConfig.getLinkByZodiac(zodiac);
        }
        if (day == Day.TOMORROW) {
            return linksTomorrowConfig.getLinkTomorrowByZodiac(zodiac);
        }
        throw new RuntimeException("Неизвестный день");
    }

}
