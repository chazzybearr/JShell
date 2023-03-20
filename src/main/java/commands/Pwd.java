package commands;


import exceptions.PwdException;
import state.ShellState;

import java.util.List;

public class Pwd extends Command{
    static int MIN_ARGUMENT = 0;
    static int MAX_ARGUMENT = 0;


    public Pwd () {
        super(MIN_ARGUMENT, MAX_ARGUMENT);
    }

    public String runCommand(ShellState state, List<String> arguments) throws PwdException {
        checkArguments(arguments);
        return state.getWorkingDirectory().getFilePath();
    }


}
