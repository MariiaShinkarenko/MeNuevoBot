package org.example.enums;

import java.util.ArrayList;
import java.util.List;

public enum Days {
    TODAY ("Сегодня"),
    TOMORROW("Завтра");
    private String name;

   Days(String name) {
        this.name = name;
    }

    public String getNameZodiac() {
        return name;
    }
    public static Days nameToDay(String name){
        for (Days days:values()){
            if (days.getNameZodiac().equals(name)){
                return days;
            }
        }
        return null;
    }
    public static List<String> getNamesDays (){
        List<String> namesOfDays = new ArrayList<>();
        for (Days days:values()){
            namesOfDays.add(days.name);
        }
        return namesOfDays;
    }
}

