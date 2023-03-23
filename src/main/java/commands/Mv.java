package commands;

import exceptions.JShellException;
import exceptions.MvException;
import exceptions.MvFileException;
import exceptions.MvOverwriteException;
import file_system.Directory;
import file_system.File;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.time.LocalDateTime;
import java.util.List;

import static lib.Constants.STDOUT;

public class Mv extends Command{
    static int MIN_ARGUMENTS = 2;
    static int MAX_ARGUMENTS = 2;

    public Mv() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);

    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {
        if (!checkArguments(arguments)) {
            throw new MvException("mv: invalid option " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\nmv: usage: mv [SOURCE] [DEST]\n");
        }

        String source = arguments.get(0);
        String destination = arguments.get(1);


        // Checking all possible error conditions
        // 1. Source does not exist
        if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), source)) {
            throw new MvFileException("mv: cannot stat " + source + "': No such file or directory\n");
        }


        // Destination does not exist, rename source to destination
        FilesysObject sourceFile = PathHelper.filesysObjectFromPath(state, source);
        if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), destination)) {
            sourceFile.setName(destination);
            LocalDateTime now = LocalDateTime.now();
            sourceFile.setModifiedTime(now);
            sourceFile.setAccessTime(now);
            return "";
        }


        // 2. Destination is a file
        FilesysObject destinationFile = PathHelper.filesysObjectFromPath(state, destination);
        if (destinationFile instanceof File) {
            throw new MvOverwriteException("mv: cannot overwrite existing non-directory '" + destination + "'\n");
        }

        // Destination is a directory
        if (destinationFile instanceof Directory) {
            if (DirectoryHelper.isInDirectory((Directory) destinationFile, source)) {
                throw new MvOverwriteException("mv: cannot move '" + source + "' to '" + destination + "': Directory contains file with same name\n");
            }
            else {
                // Removing file from working directory
                state.getWorkingDirectory().removeContent(sourceFile);
                // Adding file to destination directory
                ((Directory) destinationFile).addContent(sourceFile);
                // Updating the parent of the source file
                sourceFile.setParentDirectory((Directory) destinationFile);

                // Update timestamps
                LocalDateTime now = LocalDateTime.now();
                state.getWorkingDirectory().setModifiedTime(now);
                state.getWorkingDirectory().setAccessTime(now);

                sourceFile.setModifiedTime(now);
                sourceFile.setAccessTime(now);
            }

        }
        fileDescriptors.get(STDOUT).write("");
        return "";
    }
}
