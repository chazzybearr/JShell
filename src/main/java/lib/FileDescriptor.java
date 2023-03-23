package lib;

abstract public class FileDescriptor {

    public int fd;
    public String name;

    public FileDescriptor(int fd, String name) {
        this.fd = fd;
        this.name = name;
    }

    abstract public String read();

    abstract public void write(String output);

}
