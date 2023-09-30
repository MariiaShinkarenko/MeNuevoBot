package org.example.enums;

import java.util.ArrayList;
import java.util.List;

public enum Day {
    TODAY("Сегодня"),
    TOMORROW("Завтра");
    private String name;

    Day(String name) {
        this.name = name;
    }

    public String getNameZodiac() {
        return name;
    }

    public static Day nameToDay(String name) {
        for (Day days : values()) {
            if (days.getNameZodiac().equals(name)) {
                return days;
            }
        }
        return null;
    }

    public static List<String> getNamesDays() {
        List<String> namesOfDays = new ArrayList<>();
        for (Day days : values()) {
            namesOfDays.add(days.name);
        }
        return namesOfDays;
    }
}

