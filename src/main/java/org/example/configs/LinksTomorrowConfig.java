package org.example.configs;

import org.example.Main;
import org.example.enums.Zodiac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LinksTomorrowConfig {
    private static final String CONFIG_NAME = "LinksTomorrow.txt";
    private final Map<Zodiac, String> config;
    // конструктор без параметров
    public LinksTomorrowConfig(){
        config = readConfig();
    }
    private Map<Zodiac, String> readConfig (){
        try(InputStream io = Main.class.getClassLoader().getResourceAsStream(CONFIG_NAME)) {
            StringBuilder builder = new StringBuilder();
            if (Objects.nonNull(io)) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(io))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                }
            }

            String configString = builder.toString();
            String[] configRows = configString.split("\n");
            return buildConfig(configRows);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private Map<Zodiac, String> buildConfig(String[] configRows){
        Map<Zodiac, String> conf = new HashMap<>();
        for (String row:configRows){
            String[] keyAndValue = row.split("=");
            Zodiac key = Zodiac.valueOf(keyAndValue[0].trim());
            conf.put(key, keyAndValue[1].trim());
        }
        return conf;
    }
    public String getLinkTomorrowByZodiac (Zodiac zodiac){
        return config.get(zodiac);
    }
}


