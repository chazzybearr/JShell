package helpers;

import exceptions.CdFileException;
import file_system.Directory;
import file_system.FilesysObject;
import state.ShellState;

import java.util.Objects;

public class PathHelper {

    public static Directory directoryFromPath (ShellState state, String path) throws Exception {

        // Absolute path
        Directory curr;
        String[] steps = path.split("/");

        if (path.startsWith("/")) {
            curr = state.getFilesystem().getRoot();

            for (String step : steps) {
                for (FilesysObject child : curr.getContents()) {
                    if (Objects.equals(child.getName(), step)) {
                        curr = (Directory) child;
                        break;
                    }
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
                    for (FilesysObject child : curr.getContents()) {
                        if (Objects.equals(child.getName(), step)) {
                            curr = (Directory) child;
                            break;
                        }
                    }
                    throw new CdFileException("cd: " + step + ": No such file or directory");
                }
            }
        }
        return curr;
    }
}
