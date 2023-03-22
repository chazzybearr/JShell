package commands;

import exceptions.MkdirFileException;
import exceptions.RmException;
import file_system.Directory;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.util.List;

public class Rm extends Command{

    static int MIN_ARGUMENTS = 1;
    static int MAX_ARGUMENTS = 100;

    public Rm() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws Exception {
        if (!checkArguments(arguments)) {
            throw new RmException("rm: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\nrm: usage: rm [FILE]");
        }

        StringBuilder errorString = new StringBuilder();
        for (String file : arguments) {

            // Argument exists in the working directory
            if (DirectoryHelper.isInDirectory(state.getWorkingDirectory(), file)) {
                FilesysObject fileObj = PathHelper.filesysObjectFromPath(state, file);

                // Argument is a directory
                if (fileObj instanceof Directory) {
                    errorString.append("rm: cannot remove '").append(file).append("': Is a directory\n");
                    continue;
                }


                state.getWorkingDirectory().removeContent(fileObj);
            }
            // Argument does not exist
            else {
                errorString.append("rm: cannot remove '").append(file).append("': No such file\n");
            }
        }
        if (!errorString.isEmpty()) {
            throw new MkdirFileException(errorString.deleteCharAt(errorString.length() - 1).toString());
        }

        return "";
    }

}
