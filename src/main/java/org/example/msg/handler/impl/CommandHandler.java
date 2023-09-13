package org.example.msg.handler.impl;

import org.example.enums.Commands;
import org.example.enums.Days;
import org.example.msg.handler.MessageHandler;
import org.example.msg.keyboard.KeyBoardBuilder;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class CommandHandler implements MessageHandler {

    @Override
    public SendMessage handle(Update update) {
        String text = UpdateUtil.getTextMessage(update);
        String userName = update.getMessage().getFrom().getFirstName();
       String answer = getAnswer(text,userName);
        ReplyKeyboard replyKeyboard = KeyBoardBuilder.builder()
                .setButtonsInRaw(2)
                .setNamesButtons(Days.getNamesDays())
                .build();
        return SendMessage.builder()
                .chatId(UpdateUtil.getChatId(update))
                .text(answer)
                .replyMarkup(replyKeyboard)
                .build();
    }
    private String getAnswer(String text, String userName){
      Commands commands = Commands.nameCommand(text);
      switch (commands){
          case INFO: return "Разработан в учебных целях";
          case START: return "Приветствую,  " + userName + ". Какой прогноз Вас интересует?";
          default: return "Не знаю такой команды: " + text;
      }


    }
}
