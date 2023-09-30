package org.example.msg.handler.impl;

import org.example.enums.Command;
import org.example.enums.Menu;
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
        String answer = getAnswer(text, userName);
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

    private String getAnswer(String text, String userName) {
        Command commands = Command.nameCommand(text);
        switch (commands) {
            case INFO:
                return "Разработан в учебных целях";
            case START:
                return "Приветствую,  " + userName + ". Выберите, что вас интересует.";
            default:
                return "Не знаю такой команды: " + text;
        }


    }
}
