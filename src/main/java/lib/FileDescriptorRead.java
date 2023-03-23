package lib;

public class FileDescriptorRead extends FileDescriptor {


    public FileDescriptorRead(int fd, String name) {
        super(fd, name);
    }

    public String read() {
        return "";
    }

}
