package commands;

import exceptions.PwdException;

import java.util.List;

abstract public class Command {
    int minArguments;
    int maxArguments;

    public Command(int minArguments, int maxArguments) {
        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
    }

    public void checkArguments(List<String> arguments) throws PwdException {
        if (arguments.size() > maxArguments || arguments.size() < minArguments) {
            throw new PwdException("-jshell: pwd: " + arguments + ": invalid option\npwd: usage: pwd");
        }
    }


}
