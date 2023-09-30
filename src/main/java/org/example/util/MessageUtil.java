package org.example.util;

import org.example.enums.Command;
import org.example.enums.Day;
import org.example.enums.Menu;
import org.example.enums.Zodiac;

public final class MessageUtil {
    public static boolean isCommand(String message) {
        for (Command value : Command.values()) {
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
        for (Day value : Day.values()) {
            if (value.getNameZodiac().equals(message)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isMenu(String message) {
        for (Menu value : Menu.values()) {
            if (value.getName().equals(message)) {
                return true;
            }
        }
        return false;
    }

}

