package org.example.enums;

public enum Command {

    START("/start"),
    INFO("/info");


    private final String commandType;


    Command(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

    public static Command nameCommand(String name) {
        for (Command commands : values()) {
            if (commands.commandType.equals(name)) {
                return commands;
            }
        }
        return null;
    }

}
