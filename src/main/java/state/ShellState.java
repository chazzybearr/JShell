package state;

import file_system.Directory;
import file_system.Filesystem;

public class ShellState {
    Filesystem filesystem;
    Directory workingDirectory;
    History history;

    public ShellState() {
        filesystem = new Filesystem();
        workingDirectory = filesystem.getRoot();
        history = new History();
    }

    public Filesystem getFilesystem() {
        return filesystem;
    }

    public void setFilesystem(Filesystem filesystem) {
        this.filesystem = filesystem;
    }

    public Directory getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(Directory workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
