package org.example.msg;

import org.example.msg.handler.MessageHandler;
import org.example.msg.handler.impl.CommandHandler;
import org.example.msg.handler.impl.DayHandler;
import org.example.msg.handler.impl.ZodiacHandler;
import org.example.util.MessageUtil;
import org.example.util.UpdateUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateHandlerFacade {
private final MessageHandler commandHandler = new CommandHandler();
private final MessageHandler zodiacHandler = new ZodiacHandler();
private final MessageHandler dayHandler = new DayHandler();
public SendMessage process (Update update) {
    if (!update.hasMessage()) {
        throw new RuntimeException("update has not message");
    }
    Message inMessage = update.getMessage();
    if (!inMessage.hasText()) {
        throw new RuntimeException("message has not text");
    }
    String messageText = inMessage.getText();
    if (MessageUtil.isCommand(messageText)) {
        return commandHandler.handle(update);
    } else if (MessageUtil.isDay(messageText)) {
        return dayHandler.handle(update);
} else if (MessageUtil.isZodiac(messageText)) {
        return zodiacHandler.handle(update);
    } else {
        return SendMessage.builder()
                .chatId(UpdateUtil.getChatId(update))
                .text("Не умею обрабатывать сообщение " + messageText)
                .build();

    }


}
}
