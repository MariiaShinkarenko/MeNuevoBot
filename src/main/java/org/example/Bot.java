package org.example;

import org.example.configs.SettingsConfig;
import org.example.msg.UpdateHandlerFacade;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    private final SettingsConfig setconfig = new SettingsConfig();
    private final UpdateHandlerFacade updateHandlerFacade = new UpdateHandlerFacade();


    @Override
    public String getBotUsername() {
        return setconfig.get("BOT_NAME");
    }

    @Override
    public String getBotToken() {

        return setconfig.get("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage outMessage = updateHandlerFacade.process(update);
            execute(outMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

