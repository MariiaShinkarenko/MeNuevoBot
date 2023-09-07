package org.example.msg.handler.impl;

import org.example.cache.EventCache;
import org.example.configs.LinksTodayConfig;
import org.example.configs.LinksTomorrowConfig;
import org.example.entity.UserEvent;
import org.example.enums.Days;
import org.example.enums.Zodiac;
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

    private LinksTodayConfig linksTodayConfig = new LinksTodayConfig();
    private LinksTomorrowConfig linksTomorrowConfig = new LinksTomorrowConfig();
    private EventCache eventCache = EventCache.getInstance();


    @Override
    public SendMessage handle(Update update) {
     String textMessage  = UpdateUtil.getTextMessage(update);
        Zodiac zodiac = Zodiac.nameToZodiac(textMessage);
      String urlZodiac= getUrlDependingOnDay(zodiac);
      String answer= urlHandler.getHoroscopeFromUrl(urlZodiac);
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
    private String getUrlDependingOnDay (Zodiac zodiac){
        Days day = getDay();
        if (day == Days.TODAY){
            return linksTodayConfig.getLinkByZodiac(zodiac);
        }
        if (day == Days.TOMORROW){
            return linksTomorrowConfig.getLinkTomorrowByZodiac(zodiac);
        }
        throw new RuntimeException("Неизвестный день");
    }
    private Days getDay (){
      UserEvent lastEvent = eventCache.getLastEvent();
      if (lastEvent == null){
          return Days.TODAY;
      }
      String text= lastEvent.getText();
      if (!MessageUtil.isDay(text)){
          throw new RuntimeException("Text is not day");
      }
      return Days.nameToDay(text);
    }
}
