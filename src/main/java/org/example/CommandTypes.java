package org.example;

import java.util.HashSet;
import java.util.Set;
import static org.example.enums.Commands.INFO;
import static org.example.enums.Commands.START;

public class CommandTypes {

    public Set<String> types () {

        Set<String> types = new HashSet<String>();
        types.add(START.getCommandType());
        types.add(INFO.getCommandType());

        return types;

    }


}
