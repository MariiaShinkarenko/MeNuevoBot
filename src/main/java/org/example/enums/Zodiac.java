package org.example.enums;

public enum Zodiac {
    ARIES("Овен"),
    TAURUS("Телец"),
    GEMINI("Близнец"),
    CANCER("Рак"),
    LOE("Лев"),
    VIRGO("Дева"),
    LIBRA("Весы"),
    SCORPIO("Скорпион"),
    SAGITTARIUS("Стрелец"),
    CAPRICORN("Козерог"),
    AQUARIUS("Водолей"),
    PISCES("Рыбы");

    private String name;

    Zodiac(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static Zodiac nameToZodiac(String name){
        for (Zodiac zodiac:values()){
            if (zodiac.getName().equals(name)){
                return zodiac;
            }
        }
        return null;
    }
    }
