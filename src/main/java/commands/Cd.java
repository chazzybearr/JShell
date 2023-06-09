package commands;

import exceptions.CdException;
import exceptions.CdFileException;
import exceptions.JShellException;
import file_system.Directory;
import file_system.File;
import file_system.FilesysObject;
import helpers.PathHelper;
import state.ShellState;

import java.util.List;

import static lib.Constants.STDOUT;

public class Cd extends Command{
    static final int MIN_ARGUMENTS = 1;
    static final int MAX_ARGUMENTS = 1;

    public Cd() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }


    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {
        if (!checkArguments(arguments)) {
            throw new CdException("-jshell: cd: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\ncd: usage: cd [dir]]\n");
        }
        String path = arguments.get(0);
        FilesysObject targetDirectory = PathHelper.filesysObjectFromPath(state, path);
        if (targetDirectory instanceof File) {
            throw new CdFileException("cd: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + ": Not a directory\n");
        }
        state.setWorkingDirectory((Directory) targetDirectory);
        targetDirectory.setAccessTime();
        fileDescriptors.get(STDOUT).write("");
        return "";
    }
}
