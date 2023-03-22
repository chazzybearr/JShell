package commands;

import exceptions.MkdirFileException;
import exceptions.RmdirException;
import file_system.Directory;
import file_system.File;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.util.List;

public class Rmdir extends Command{

    static int MIN_ARGUMENTS = 1;
    static int MAX_ARGUMENTS = 100;

    public Rmdir() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws Exception {
        if (!checkArguments(arguments)) {
            throw new RmdirException("rmdir: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\nrmdir: usage: rmdir [DIR]");
        }

        StringBuilder errorString = new StringBuilder();
        for (String directory : arguments) {


            // Argument exists in the working directory
            if (DirectoryHelper.isInDirectory(state.getWorkingDirectory(), directory)) {
                FilesysObject directoryObj = PathHelper.filesysObjectFromPath(state, directory);
                // Target is a file
                if (directoryObj instanceof File) {
                    errorString.append("rmdir: Failed to remove '").append(directory).append("': Not a directory\n");
                    continue;
                }

                // Target directory not empty
                if (!DirectoryHelper.isEmpty((Directory) directoryObj)) {
                    errorString.append("rmdir: cannot remove '").append(directory).append("': Directory not empty\n");
                    continue;
                }

                state.getWorkingDirectory().removeContent(directoryObj);
            }
            // Argument does not exist
            else {
                errorString.append("rmdir: cannot remove '").append(directory).append("': No such directory\n");
            }
        }
        if (!errorString.isEmpty()) {
            throw new MkdirFileException(errorString.deleteCharAt(errorString.length() - 1).toString());
        }

        return "";
    }

}
