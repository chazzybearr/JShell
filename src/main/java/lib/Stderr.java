package lib;

import static lib.Constants.STDERR;

public class Stderr extends FileDescriptorWrite{
    public Stderr() {
        super(STDERR, "STDERR");
    }
}
