package file_system;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FilesysObject{
    List<FilesysObject> children;

    public Directory(String name, Directory parentDirectory) {
        super(name);
        this.parentDirectory = parentDirectory;
        this.children = new ArrayList<>();
        this.children.add(this);
        this.children.add(this.parentDirectory);
    }

    public List<FilesysObject> getContents() {
        return children;
    }

    public void addContent(FilesysObject content) {
        this.children.add(content);
        content.parentDirectory = this;
    }

    public void removeContent(FilesysObject content) {
        this.children.remove(content);
    }

    @Override
    public String getName() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";

        return ANSI_BLUE + this.name + ANSI_RESET;
    }
}
