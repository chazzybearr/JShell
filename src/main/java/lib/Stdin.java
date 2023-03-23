package lib;

import static lib.Constants.STDIN;

public class Stdin extends FileDescriptorRead{
    public Stdin() {
        super(STDIN, "STDIN");
    }
}
