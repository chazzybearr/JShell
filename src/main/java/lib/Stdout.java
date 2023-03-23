package lib;

import static lib.Constants.STDOUT;

public class Stdout extends FileDescriptorWrite{
    public Stdout() {
        super(STDOUT, "STDOUT");
    }
}
