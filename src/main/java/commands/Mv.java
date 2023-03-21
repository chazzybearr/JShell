package commands;

import exceptions.MvException;
import exceptions.MvFileException;
import exceptions.MvOverwriteException;
import file_system.Directory;
import file_system.File;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.util.List;

public class Mv extends Command{
    static int MIN_ARGUMENTS = 2;
    static int MAX_ARGUMENTS = 2;

    public Mv() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);

    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws Exception {
        if (!checkArguments(arguments)) {
            throw new MvException("mv: invalid option " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\nmv: usage: mv [SOURCE] [DEST]");
        }

        String source = arguments.get(0);
        String destination = arguments.get(1);


        // Checking all possible error conditions
        // 1. Source does not exist
        if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), source)) {
            throw new MvFileException("mv: cannot stat " + source + "': No such file or directory");
        }


        // Destination does not exist, rename source to destination
        FilesysObject sourceFile = PathHelper.filesysObjectFromPath(state, source);
        if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), destination)) {
            sourceFile.setName(destination);
            return "";
        }


        // 2. Destination is a file
        FilesysObject destinationFile = PathHelper.filesysObjectFromPath(state, destination);
        if (destinationFile instanceof File) {
            throw new MvOverwriteException("mv: cannot overwrite existing non-directory '" + destination + "'");
        }

        // Destination is a directory
        if (destinationFile instanceof Directory) {
            if (DirectoryHelper.isInDirectory((Directory) destinationFile, source)) {
                throw new MvOverwriteException("mv: cannot move '" + source + "' to '" + destination + "': Directory contains file with same name");
            }
            else {
                // Removing file from working directory
                state.getWorkingDirectory().removeContent(sourceFile);
                // Adding file to destination directory
                ((Directory) destinationFile).addContent(sourceFile);
                // Updating the parent of the source file
                sourceFile.setParentDirectory((Directory) destinationFile);
            }

        }

        return "";
    }
}
