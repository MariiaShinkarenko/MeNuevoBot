package org.example.configs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CharacteristicsConfig {
    private static final String SETCONFIG_NAME = "Characteristic.txt";
    private final Map<String, String> Setconfig;

    // Constructor
    public CharacteristicsConfig() {

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
                setConf.put(keyAndValue[0].trim(), keyAndValue[1].trim());
            }
        }
        return setConf;

    }
    public String get (String key){
        return Setconfig.get(key);

    }
}


