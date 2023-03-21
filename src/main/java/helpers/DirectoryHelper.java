package helpers;

import file_system.Directory;
import file_system.FilesysObject;

import java.util.Objects;

public class DirectoryHelper {

    public static boolean isInDirectory(Directory workingDirectory, String name) {

        for (FilesysObject child : workingDirectory.getContents()) {
            if (Objects.equals(child.getName(), name)) {
                return true;
            }
        }
        return false;
    }
}
