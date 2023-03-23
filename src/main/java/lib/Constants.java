package lib;

import commands.*;

import java.util.HashMap;

public class Constants {
    public static HashMap<String, Command> COMMAND_FROM_STR = new HashMap<>();

    static {
        COMMAND_FROM_STR.put("cat", new Cat());
        COMMAND_FROM_STR.put("cd", new Cd());
        COMMAND_FROM_STR.put("echo", new Echo());
        COMMAND_FROM_STR.put("ls", new Ls());
        COMMAND_FROM_STR.put("mkdir", new Mkdir());
        COMMAND_FROM_STR.put("mv", new Mv());
        COMMAND_FROM_STR.put("pwd", new Pwd());
        COMMAND_FROM_STR.put("rm", new Rm());
        COMMAND_FROM_STR.put("rmdir", new Rmdir());
        COMMAND_FROM_STR.put("touch", new Touch());
    }

    public static int STDIN = 0;
    public static int STDOUT = 1;
    public static int STDERR = 2;

}
