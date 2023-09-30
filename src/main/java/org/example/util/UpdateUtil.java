package org.example.util;

import org.telegram.telegrambots.meta.api.objects.Update;

public final class UpdateUtil {
    public static String getTextMessage(Update update) {
        return update.getMessage().getText();
    }

    public static long getChatId(Update update) {
        return update.getMessage().getChatId();
    }
}

