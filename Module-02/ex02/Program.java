import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Run : java Program --current-folder=/Users/Admin");
            System.exit(-1);
        }
        Path path = Path.of(args[0]);
        if (Files.isDirectory(path)) {
            System.out.println(args[0]);
            FileManager fm = new FileManager(args[0]);
            fm.start();
        } else {
            System.out.println("Not a valid directory");
            System.exit(-1);
        }
    }
}