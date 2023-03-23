package commands;

import exceptions.JShellException;
import exceptions.LsException;
import file_system.FilesysObject;
import state.ShellState;

import java.time.LocalDateTime;
import java.util.List;

public class Ls extends Command {

    static int MIN_ARGUMENT = 0;
    static int MAX_ARGUMENT = 0;


    public Ls () {
        super(MIN_ARGUMENT, MAX_ARGUMENT);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {
        if (!checkArguments(arguments)) {
            throw new LsException("-jshell: ls: invalid option: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "\nls: usage: ls\n");
        }

        StringBuilder contents = new StringBuilder();
        for (FilesysObject child : state.getWorkingDirectory().getContents()) {

            if (child != null && child != state.getWorkingDirectory() && child != state.getWorkingDirectory().getParentDirectory()) {
                contents.append(child.getColorName()).append("\t");
            }

//          Lines to show hidden folders (future implementation)
//            if (child == null) {
//                contents.append("..").append("\t");
//                continue;
//            }
//
//            if (child == state.getWorkingDirectory().getParentDirectory()) {
//                contents.append("..").append("\t");
//                continue;
//            }
//
//            if (child == state.getWorkingDirectory()) {
//                contents.append(".").append("\t");
//                continue;
//            }

        }
        if (!contents.isEmpty()) {
            contents.append('\n');
        }
        state.getWorkingDirectory().setAccessTime(LocalDateTime.now());
        return contents.toString();
    }

}
