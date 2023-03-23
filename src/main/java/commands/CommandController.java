package commands;

import exceptions.JShellException;
import lib.Constants;
import lib.Redirect;
import state.ShellState;

import java.util.List;

public class CommandController {

    public String runCommand(ShellState state, String comm, List<String> args) {

        String ret;
        Command command = Constants.COMMAND_FROM_STR.get(comm);

        if (command == null) {
            System.out.print(comm + ": command not found\n");
            return "";
        }

        try {
            if (args.contains(">") || args.contains("<")) {
                Redirect redirect = new Redirect(command);
                ret = redirect.runCommand(state, args);
            } else {
                ret = command.runCommand(state, args);
            }
        } catch (JShellException ex) {
            ret = ex.getMessage();
        }
        return ret;

    }

}
