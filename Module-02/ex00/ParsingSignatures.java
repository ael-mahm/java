import java.util.LinkedHashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class ParsingSignatures {
    private Map<String, byte[]> map;

    public ParsingSignatures() {
        this.map = new LinkedHashMap<>();
    }

    public void readFile() throws Exception {
        FileInputStream fs = new FileInputStream("signatures.txt");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int _byte;
        while ((_byte = fs.read()) != -1) {
            buffer.write(_byte);
        }
        fs.close();
        String file = buffer.toString("UTF-8");
        String[] lines = file.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split(",");
            if (parts.length < 2)
                continue;
            String typeFile = parts[0].trim();
            String[] values = parts[1].trim().split(" ");
            byte[] bytesValues = new byte[values.length];
            for (int j = 0; j < values.length; j++) {
                bytesValues[j] = (byte) Integer.parseInt(values[j], 16);
            }
            this.map.put(typeFile, bytesValues);
        }
    }

    public static boolean compareBytes(byte[] file, byte[] signature) {
        if (file.length < signature.length)
            return false;

        for (int i = 0; i < signature.length; i++) {
            if (file[i] != signature[i]) {
                return false;
            }
        }
        return true;
    }

    public void result(String type) throws Exception {
        FileOutputStream fs = new FileOutputStream("result.txt", true);
        fs.write((type + "\n").getBytes());
        fs.close();
        System.out.println("PROCESSED");
    }

    public void readPath(String path) throws Exception {
        int maxLength = 0;

        for (byte[] arr : this.map.values()) {
            if (arr.length > maxLength) {
                maxLength = arr.length;
            }
        }

        try (FileInputStream fs = new FileInputStream(path)) {
            byte[] buffer = new byte[maxLength];
            int bytesRead = fs.read(buffer);

            if (bytesRead == -1) {
                throw new Exception("file ---- x");
            }

            for (String type : this.map.keySet()) {
                if (compareBytes(buffer, this.map.get(type))) {
                    result(type);
                    return;
                }
            }
            result("UNDEFINED");
        } catch (Exception e) {
            result("UNDEFINED");
        }

    }
}