package commands;

import exceptions.CatException;
import exceptions.CatFileException;
import exceptions.JShellException;
import file_system.Directory;
import file_system.File;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.time.LocalDateTime;
import java.util.List;

import static lib.Constants.STDOUT;

public class Cat extends Command{

    static int MIN_ARGUMENTS = 1;
    static int MAX_ARGUMENTS = 100;

    public Cat() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {
        if (!checkArguments(arguments)) {
            throw new CatException("cat: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\ncat: usage: cat [FILE]\n");
        }

        StringBuilder returnString = new StringBuilder();
        StringBuilder errorString = new StringBuilder();
        for (String file : arguments) {


            // Argument exists in the working directory
            if (DirectoryHelper.isInDirectory(state.getWorkingDirectory(), file)) {
                FilesysObject fileObj = PathHelper.filesysObjectFromPath(state, file);
                // Target is a directory
                if (fileObj instanceof Directory) {
                    errorString.append("cat: ").append(file).append(": Is a directory\n");
                    continue;
                }
                returnString.append(((File)fileObj).getContents()).append("\n");
                LocalDateTime now = LocalDateTime.now();
                fileObj.setAccessTime(now);
                fileObj.setModifiedTime(now);
            }
            // Argument does not exist
            else {
                errorString.append("cat: '").append(file).append("': No such file or directory\n");
            }
        }
        if (!errorString.isEmpty()) {
            throw new CatFileException(errorString.toString());
        }

        fileDescriptors.get(STDOUT).write(returnString.deleteCharAt(returnString.length() - 1).toString());
        return returnString.deleteCharAt(returnString.length() - 1).toString();
    }

}
