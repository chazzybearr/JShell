package lib;

import static lib.Constants.STDOUT;

public class Stdout extends FileDescriptorWrite{
    public Stdout() {
        super(STDOUT, "STDOUT");
    }

    @Override
    public void write(String output) {
        System.out.print(output);
    }
}
