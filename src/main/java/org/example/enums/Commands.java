package org.example.enums;

public enum Commands {

    START("/start"),
    INFO("/info");


    private final String commandType;


    Commands(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

    public static Commands nameCommand(String name) {
        for (Commands commands : values()) {
            if (commands.commandType.equals(name)) {
                return commands;
            }
        }
        return null;
    }

}
