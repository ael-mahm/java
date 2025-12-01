import java.io.FileOutputStream;
import java.io.IOException;

class Program {

    public static void resetResult() {
        try {
            FileOutputStream fs = new FileOutputStream("result.txt", false);
            fs.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        resetResult();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        try {
            ParsingSignatures ps = new ParsingSignatures();
            ps.readFile();
            while (true) {
                String line = scanner.nextLine();
                line = line.trim();
                if (line.equals("42"))
                    break;
                ps.readPath(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}