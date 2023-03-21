package commands;

import state.ShellState;

import java.util.List;

abstract public class Command {
    int MIN_ARGUMENTS;
    int MAX_ARGUMENTS;

    public Command(int minArguments, int maxArguments) {
        MIN_ARGUMENTS = minArguments;
        MAX_ARGUMENTS = maxArguments;
    }

    public boolean checkArguments(List<String> arguments) {
        return arguments.size() <= MAX_ARGUMENTS && arguments.size() >= MIN_ARGUMENTS;
    }

    abstract String runCommand(ShellState state, List<String> arguments) throws Exception;


}
