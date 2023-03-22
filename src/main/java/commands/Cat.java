package commands;

import exceptions.CatException;
import exceptions.CatFileException;
import file_system.Directory;
import file_system.File;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.util.List;

public class Cat extends Command{

    static int MIN_ARGUMENTS = 1;
    static int MAX_ARGUMENTS = 100;

    public Cat() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws Exception {
        if (!checkArguments(arguments)) {
            throw new CatException("cat: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\ncat: usage: cat [FILE]");
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
            }
            // Argument does not exist
            else {
                errorString.append("cat: '").append(file).append("': No such file or directory\n");
            }
        }
        if (!errorString.isEmpty()) {
            throw new CatFileException(errorString.deleteCharAt(errorString.length() - 1).toString());
        }

        return returnString.toString();
    }

}
