package commands;

import state.ShellState;

import java.util.List;

import static lib.Constants.STDOUT;

public class Echo extends Command{
    static int MIN_ARGUMENTS = 0;
    static int MAX_ARGUMENTS = 100;

    public Echo() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) {

        StringBuilder returnString = new StringBuilder();
        for (String word : arguments) {
            returnString.append(word).append(" ");
        }
        if (returnString.isEmpty()) {
            return "\n";
        }
        fileDescriptors.get(STDOUT).write(returnString.deleteCharAt(returnString.length() - 1).append("\n").toString());
        return returnString.deleteCharAt(returnString.length() - 1).append("\n").toString();
    }

}
