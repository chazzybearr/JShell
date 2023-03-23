package commands;


import exceptions.PwdException;
import state.ShellState;

import java.util.List;

import static lib.Constants.STDOUT;

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
        fileDescriptors.get(STDOUT).write(state.getWorkingDirectory().getFilePath() + "\n");
        return state.getWorkingDirectory().getFilePath() + "\n";
    }


}
