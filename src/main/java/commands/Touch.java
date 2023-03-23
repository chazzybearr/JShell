package commands;

import exceptions.JShellException;
import exceptions.TouchException;
import file_system.File;
import file_system.FilesysObject;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.time.LocalDateTime;
import java.util.List;

import static lib.Constants.STDOUT;

public class Touch extends Command{
    static int MIN_ARGUMENTS = 1;
    static int MAX_ARGUMENTS = 100;

    public Touch() {
        super(MIN_ARGUMENTS, MAX_ARGUMENTS);
    }

    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {
        if (!checkArguments(arguments)) {
            throw new TouchException("touch: " + arguments.toString().replace(",", "").replace("[", "").replace("]", "") + "invalid option\ntouch: usage: touch [FILE]");
        }

        for (String file : arguments) {

            // Argument is not already in the directory
            if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), file)) {
                File newFile = new File(file);
                state.getWorkingDirectory().addContent(newFile);
            }
            // Argument already exists in the directory
            else {
                FilesysObject filesysObject = PathHelper.filesysObjectFromPath(state, file);
                LocalDateTime now = LocalDateTime.now();
                filesysObject.setAccessTime(now);
                filesysObject.setModifiedTime(now);

            }
        }
        fileDescriptors.get(STDOUT).write("");
        return "";
    }
}
