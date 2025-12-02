import java.io.File;

public class FileManager {

    File file;

    public FileManager(String pathname) {
        this.file = new File(pathname);
    }

    public String[] parseCommand(String line) throws Exception {
        String[] parts = line.split(" ");
        switch (parts[0].toLowerCase()) {
            case "ls":
                if (parts.length > 1)
                    throw new Exception("[x] use ls without args.");
                break;
            case "mv":
                if (parts.length != 3)
                    throw new Exception("[x] mv WHAT WHERE.");
                break;
            case "cd":
                if (parts.length != 2)
                    throw new Exception("[x] cd FOLDER_NAME.");
            default:
                return null;
        }
        return parts;
    }

    public void start() {
        while (true) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String line = scanner.nextLine().trim();
            if (line.equals("exit")) {
                scanner.close();
                break;
            }
            if (line.equals(""))
                continue;
            try {
                String[] command = parseCommand(line);
                if (command == null) {
                    System.out.println("[x] UNDEFINED command.");
                    continue;
                }
                if (command[0].equals("ls")) {
                    File[] files = this.file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(files[i].getName() + " " + file.length() / 1024 + " KB");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}