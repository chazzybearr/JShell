package lib;

import commands.Command;

import exceptions.JShellException;
import state.ShellState;

import java.util.ArrayList;
import java.util.List;


public class Pipe2 extends Command{
    Command command;
    public Pipe2(Command command) {
        super(0, 0);
        this.command = command;
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {

        List<String> commandOneArguments;
        String commandTwoStr;
        Command commandTwo;
        List<String> commandTwoArguments;
        String returnString = "";


        // Setting up command line arguments
        commandOneArguments = arguments.subList(0, arguments.indexOf("|"));
        commandTwoStr = arguments.get(arguments.indexOf("|") + 1);
        commandTwo = Constants.get(commandTwoStr);

        // There are no arguments for command two
        if (arguments.size() - 1 == arguments.indexOf(commandTwoStr)) {
            commandTwoArguments = new ArrayList<>();
        } else {
            commandTwoArguments = arguments.subList(arguments.indexOf(commandTwoStr) + 1, arguments.size());
        }


        // Setting up the pipe
        Pipe pipe = new Pipe();
        command.setStdout(pipe);
        commandTwo.setStdin(pipe);

        command.runCommand(state, commandOneArguments);


        return returnString;
    }

}
