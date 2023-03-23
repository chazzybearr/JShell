package lib;

import commands.Command;
import exceptions.JShellException;
import file_system.File;
import helpers.DirectoryHelper;
import helpers.PathHelper;
import state.ShellState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Redirect extends Command {
    Command command;
    public Redirect(Command command) {
        super(0, 0);
        this.command = command;
    }


    @Override
    public String runCommand(ShellState state, List<String> arguments) throws JShellException {

        String option;
        String file;
        String returnString = "";


        if (arguments.contains("<")) {
            option = "<";
        } else {
            option = ">";
        }

        file = arguments.get(arguments.indexOf(option) + 1);


        switch (option) {
            // Input redirection
            case "<": {
                ArrayList<String> args = new ArrayList<>() {{
                    add(file);
                }};
                Command cat = Constants.COMMAND_FROM_STR.get("cat");
                List<String> commandArguments = Arrays.asList(cat.runCommand(state, args).split(" "));
                returnString = command.runCommand(state, commandArguments);
            }

            // Output redirection
            case ">": {

                // Set the result of the command as file contents
                List<String> commandArguments = arguments.subList(0, arguments.indexOf(option));
                String returnedString = command.runCommand(state, commandArguments);

                // File does not exist
                if (!DirectoryHelper.isInDirectory(state.getWorkingDirectory(), file)) {
                    Command touch = Constants.COMMAND_FROM_STR.get("touch");
                    ArrayList<String> args = new ArrayList<>() {{
                        add(file);
                    }};
                    touch.runCommand(state, args);

                    File fileObject = (File) PathHelper.filesysObjectFromPath(state, file);
                    fileObject.setContents(returnedString);
                    LocalDateTime now = LocalDateTime.now();
                    fileObject.setAccessTime(now);
                    fileObject.setModifiedTime(now);

                    // File exists, overwrite contents
                } else {
                    File fileObject = (File) PathHelper.filesysObjectFromPath(state, file);
                    fileObject.setContents(returnedString);
                    LocalDateTime now = LocalDateTime.now();
                    fileObject.setAccessTime(now);
                    fileObject.setModifiedTime(now);
                }
            }
        }
        return returnString;
    }
}
