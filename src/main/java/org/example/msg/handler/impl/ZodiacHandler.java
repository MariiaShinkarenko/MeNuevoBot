package org.example.msg.handler.impl;

import org.example.configs.LinksTodayConfig;
import org.example.configs.LinksTomorrowConfig;
import org.example.enums.Zodiac;
import org.example.msg.UrlHandlerToday;
import org.example.msg.UrlHandlerTomorrow;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ZodiacHandler implements MessageHandler {
    private UrlHandlerToday urlHandlerToday = new UrlHandlerToday();

    private LinksTodayConfig linksTodayConfig = new LinksTodayConfig();


    @Override
    public SendMessage handle(Update update) {
     String textMessage  = UpdateUtil.getTextMessage(update);
        Zodiac zodiac = Zodiac.nameToZodiac(textMessage);
      String urlZodiacToday  = linksTodayConfig.getLinkByZodiac(zodiac);
      String answer= urlHandlerToday.getHoroscopeFromUrl(urlZodiacToday);
        ReplyKeyboard replyKeyboard =KeyBoardBuilder.builder()
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
