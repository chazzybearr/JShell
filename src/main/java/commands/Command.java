package commands;

import java.util.List;

abstract public class Command {
    int minArguments;
    int maxArguments;

    public Command(int minArguments, int maxArguments) {
        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
    }

    boolean checkArguments(List<String> arguments) {
        return arguments.size() <= maxArguments && arguments.size() >= minArguments;
    }


}
