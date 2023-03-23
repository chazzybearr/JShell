package lib;

import commands.*;

public class Constants {

    public static Command get(String command) {
        return switch (command) {
            case "cat" -> new Cat();
            case "cd" -> new Cd();
            case "echo" -> new Echo();
            case "ls" -> new Ls();
            case "mkdir" -> new Mkdir();
            case "mv" -> new Mv();
            case "pwd" -> new Pwd();
            case "rm" -> new Rm();
            case "rmdir" -> new Rmdir();
            case "touch" -> new Touch();
            default -> null;
        };
    }

    public static int STDIN = 0;
    public static int STDOUT = 1;
    public static int STDERR = 2;

}
