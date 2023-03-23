package lib;

public class Pipe extends FileDescriptor {

    String buffer;
    FileDescriptorRead readDescriptor;
    FileDescriptorWrite writeDescriptor;

    public Pipe() {
        super(3, "pipe");
    }

    public void setReadFd(FileDescriptorRead readFd) {
        this.readDescriptor = readFd;
    }

    public void setWriteFd(FileDescriptorWrite writeFd) {
        this.writeDescriptor = writeFd;
    }

    public String read() {
        return buffer;
    }

    public void write(String buffer) {
        this.buffer = buffer;
    }

}
