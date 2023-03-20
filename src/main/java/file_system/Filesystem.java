package file_system;


public class Filesystem {
    private final Directory root;

    public Directory getRoot() {
        return root;
    }

    public Filesystem() {
        this.root = new Directory("/", null) ;
    }

}
