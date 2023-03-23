package lib;

public class FileDescriptorWrite extends FileDescriptor {

    public FileDescriptorWrite(int fd, String name) {
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
