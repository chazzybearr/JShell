package commands;

import exceptions.MkdirException;
import exceptions.MkdirFileException;
import file_system.Directory;
import helpers.DirectoryHelper;
import state.ShellState;

import java.util.List;

public class Mkdir extends Command{
    static int MIN_ARGUMENTS = 1;
    static int MAX_ARGUMENTS = 100;

    public Mkdir() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws Exception {
        if (!checkArguments(arguments)) {
            throw new MkdirException("mkdir: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\nmkdir: usage: mkdir [dir]");
        }

        StringBuilder errorString = new StringBuilder();
        for (String dir : arguments) {

            // Argument is not already in the directory
            if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), dir)) {
                Directory newDirectory = new Directory(dir, state.getWorkingDirectory());
                state.getWorkingDirectory().addContent(newDirectory);
            }
            // Argument already exists in the directory
            else {
                errorString.append("mkdir: cannot create directory '").append(dir).append("': File exists\n");
            }
        }
        if (!errorString.isEmpty()) {
            throw new MkdirFileException(errorString.deleteCharAt(errorString.length() - 1).toString());
        }

        return "";
    }
}
