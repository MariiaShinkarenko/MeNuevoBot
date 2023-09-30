package org.example.enums;

import java.util.ArrayList;
import java.util.List;

public enum Menu {
    HOROSCOPE("Гороскоп"),
    CHARACTERISTIC("Характеристика");
    private String name;

    Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Menu nameToMenu(String name) {
        for (Menu menu : values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public static List<String> getNamesMenu() {
        List<String> namesOfMenu = new ArrayList<>();
        for (Menu menu : values()) {
            namesOfMenu.add(menu.name);
        }
        return namesOfMenu;
    }
}
