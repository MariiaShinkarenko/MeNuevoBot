package org.example.msg.handler.impl;

import org.example.configs.LinksTomorrowConfig;
import org.example.enums.Zodiac;
import org.example.msg.UrlHandlerTomorrow;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ZodiacTomorrowHandler implements MessageHandler {
    private UrlHandlerTomorrow urlHandlerTomorrow = new UrlHandlerTomorrow();
    private LinksTomorrowConfig linksTomorrowConfig = new LinksTomorrowConfig();

    @Override
    public SendMessage handle(Update update) {
        String textMessage = UpdateUtil.getTextMessage(update);
        Zodiac zodiac = Zodiac.nameToZodiac(textMessage);
        String urlZodiacTomorrow = linksTomorrowConfig.getLinkTomorrowByZodiac(zodiac);
        String answer = urlHandlerTomorrow.getHoroscopeFromUrl(urlZodiacTomorrow);
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
}

