package lib;

public class FileDescriptorRead extends FileDescriptor {


    public FileDescriptorRead(int fd, String name) {
        super(fd, name);
    }

    @Override
    public String read() {
        return null;
    }

    @Override
    public void write(String output) {

    }


}
