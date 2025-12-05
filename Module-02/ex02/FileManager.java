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
                break;
            default:
                return null;
        }
        return parts;
    }

    public void mvCommand(String[] cmd) {
        try {
            File sr = new File(this.file, cmd[1]);
            sr = sr.getCanonicalFile();
            System.out.println(sr.getCanonicalFile());
            if (!sr.exists() || !sr.isFile()) {
                throw new Exception("File not found!");
            }
            File dst;
            if (cmd[2].startsWith("/")) {
                dst = new File(cmd[2]);
            } else {
                dst = new File(this.file, cmd[2]);
            }
            dst = dst.getCanonicalFile();
            if (dst.exists() && dst.isDirectory()) {
                dst = new File(dst, sr.getName());
            } else {
                dst = new File(this.file, cmd[2]).getCanonicalFile();
            }
            if (!(sr.renameTo(dst))) {
                throw new Exception("Failed mv command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cdCommand(String[] cmd) {
        File folder;

        if (cmd[1].equals("..")) {
            folder = this.file.getParentFile();
        } else {
            folder = new File(this.file, cmd[1]);
        }

        try {
            folder = file.getCanonicalFile();
            System.out.println(folder.getAbsolutePath());
        } catch (Exception e) {
        }
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
                if (command[0].equals("cd")) {
                    cdCommand(command);
                }
                if (command[0].equals("mv")) {
                    mvCommand(command);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}