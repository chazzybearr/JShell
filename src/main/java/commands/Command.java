package commands;

import exceptions.JShellException;
import lib.*;
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

    public void setStdin(FileDescriptor newFd) {
        fileDescriptors.put(STDIN, newFd);
    }

    public void setStdout(FileDescriptor newFd) {
        fileDescriptors.put(STDOUT, newFd);
    }

    public void setStderr(FileDescriptor newFd) {
        fileDescriptors.put(STDERR, newFd);
    }

    public String read() {
        return fileDescriptors.get(STDIN).read();
    }

    public void write(String output) {
        fileDescriptors.get(STDOUT).write(output);
    }

    public FileDescriptor getFd(int fd) {
        return fileDescriptors.get(fd);
    }

    abstract public String runCommand(ShellState state, List<String> arguments) throws JShellException;



}
