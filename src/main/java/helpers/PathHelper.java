package helpers;

import exceptions.FileException;
import exceptions.JShellException;
import file_system.Directory;
import file_system.FilesysObject;
import state.ShellState;

import java.util.Objects;

public class PathHelper {

    public static FilesysObject filesysObjectFromPath (ShellState state, String path) throws JShellException {

        // Absolute path
        FilesysObject curr;
        String[] steps = path.split("/");

        if (path.startsWith("/")) {
            curr = state.getFilesystem().getRoot();

            for (String step : steps) {
                boolean found = false;
                for (FilesysObject child : ((Directory) curr).getContents()) {
                    if (Objects.equals(child.getName(), step)) {
                        curr = child;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new FileException("cd: " + step + ": No such file or directory\n");
                }
            }
        }

        // Relative path
        else {
            curr = state.getWorkingDirectory();
            for (String step : steps) {

                if (Objects.equals(step, "..")) {
                    curr = curr.getParentDirectory();
                }

                else if (Objects.equals(step, ".")) {
                    continue;
                }

                else {
                    boolean found = false;
                    for (FilesysObject child : ((Directory) curr).getContents()) {
                        if (Objects.equals(child.getName(), step)) {
                            curr = child;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        throw new FileException(step + ": No such file or directory\n");
                    }
                }
            }
        }
        return curr;
    }
}
