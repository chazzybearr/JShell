package commands;

import exceptions.JShellException;
import lib.*;
import state.ShellState;

import java.util.List;

import static lib.Constants.STDERR;

public class CommandController {

    public void runCommand(ShellState state, String comm, List<String> args) {

        Command command = Constants.get(comm);

        if (command == null) {
            System.out.print(comm + ": command not found\n");
            return;
        }

        try {
            if (args.contains(">") || args.contains("<")) {
                Redirect redirect = new Redirect(command);
                redirect.runCommand(state, args);
            } else if (args.contains("|")) {
                Pipe2 pipe2 = new Pipe2(command);
                pipe2.runCommand(state, args);
            } else {
                command.runCommand(state, args);
            }
        } catch (JShellException ex) {
            command.getFd(STDERR).write(ex.getMessage());

        }

    }

}
