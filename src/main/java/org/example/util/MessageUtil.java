package org.example.util;

import org.example.enums.Commands;
import org.example.enums.Days;
import org.example.enums.Zodiac;

public final class MessageUtil {
    public static boolean isCommand(String message) {
        for (Commands value : Commands.values()) {
            if (value.getCommandType().equals(message)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isZodiac(String message) {
        for (Zodiac value : Zodiac.values()) {
            if (value.getName().equals(message)) {
                return true;
            }

        }
        return false;

    }

    public static boolean isDay(String message) {
        for (Days value : Days.values()) {
            if (value.getNameZodiac().equals(message)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isCharacteristic(String message) {
        for (Zodiac value : Zodiac.values()) {
            if (value.getName().equals(message)) {
                return true;
            }
        }
            return false;

    }
}