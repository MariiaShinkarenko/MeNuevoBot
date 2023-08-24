package org.example.configs;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class SettingsConfig {

    @Value("${bot.name}")
     public String BOT_NAME;

    @Value("${bot.token}")
    public String BOT_TOKEN;

    private static final String SETCONFIG_NAME = "Settings.txt";
    private final Map<String, String> Setconfig;

    // Constructor
    public SettingsConfig() {
        Setconfig = readConfig();
    }

    private Map<String, String> readConfig() {
        try (InputStream io = SettingsConfig.class.getClassLoader().getResourceAsStream(SETCONFIG_NAME)) {
            StringBuilder builder = new StringBuilder();
            if (Objects.nonNull(io)) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(io))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                }
            }

            String SetconfigString = builder.toString();
            String[] SetconfigRows = SetconfigString.split("\n");
            return buildConfig(SetconfigRows);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> buildConfig(String[] configRows) {
        Map<String, String> setConf = new HashMap<>();
        for (String row : configRows) {
            String[] keyAndValue = row.split("=");
            if (keyAndValue.length >= 2) {
                setConf.put(keyAndValue[0], keyAndValue[1].trim());
            }
        }
        return setConf;
    }
}
