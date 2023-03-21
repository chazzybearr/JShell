package file_system;

import java.nio.charset.StandardCharsets;

public class File extends FilesysObject{
    String contents;
    long size;

    public File(String name) {
        super(name);
        this.contents = "";

    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
        updateSize();
    }

    public long getSize() {
        return size;
    }

    public void updateSize() {
        this.size = contents.getBytes(StandardCharsets.UTF_16).length;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getColorName() {
        return getName();
    }
}
