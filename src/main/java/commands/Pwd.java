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

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws PwdException {
        if (!checkArguments(arguments)) {
            throw new PwdException("-jshell: pwd: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + ": invalid option\npwd: usage: pwd");
        }
        return state.getWorkingDirectory().getFilePath() + "\n";
    }


}
