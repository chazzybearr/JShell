package commands;

import exceptions.JShellException;
import lib.FileDescriptor;
import lib.Stderr;
import lib.Stdin;
import lib.Stdout;
import state.ShellState;

import java.util.HashMap;
import java.util.List;

import static lib.Constants.*;

abstract public class Command {
    int MIN_ARGUMENTS;
    int MAX_ARGUMENTS;
    HashMap<Integer, FileDescriptor> fileDescriptors = new HashMap<>();


    public Command(int minArguments, int maxArguments) {
        MIN_ARGUMENTS = minArguments;
        MAX_ARGUMENTS = maxArguments;
        fileDescriptors.put(STDIN, new Stdin());
        fileDescriptors.put(STDOUT, new Stdout());
        fileDescriptors.put(STDERR, new Stderr());
    }

    public boolean checkArguments(List<String> arguments) {
        return arguments.size() <= MAX_ARGUMENTS && arguments.size() >= MIN_ARGUMENTS;
    }

    abstract public String runCommand(ShellState state, List<String> arguments) throws JShellException;

    private void sendToStdout(String output) {

    }


}
