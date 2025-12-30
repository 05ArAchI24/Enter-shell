import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;

public class FileUtilsModule implements Module {
    public String getName() { return "FileUtilsModule"; }
    public String getVersion() { return "1.0"; }
    public String getAuthor() { return "arach"; }

    public void onLoad() { System.out.println("📁 FileUtilsModule loaded!"); }
    public void onUnload() { System.out.println("📁 FileUtilsModule unloaded!"); }

    public Map<String, Command> getCommands() {
        Map<String, Command> cmds = new HashMap<>();
        cmds.put("md5", new MD5Command());
        cmds.put("count", new CountCommand());
        cmds.put("backup", new BackupCommand());
        return cmds;
    }

    class MD5Command implements Command {
        public String getName() { return "md5"; }
        public String getDescription() { return "Calculate MD5 hash of file"; }
        public void execute(String[] args) {
            if (args.length > 0) {
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    File file = new File(args[0]);
                    if (!file.exists()) {
                        System.out.println("File not found: " + args[0]);
                        return;
                    }

                    byte[] data = Files.readAllBytes(file.toPath());
                    byte[] hash = md.digest(data);

                    StringBuilder hex = new StringBuilder();
                    for (byte b : hash) {
                        hex.append(String.format("%02x", b));
                    }
                    System.out.println("MD5: " + hex.toString());

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Usage: md5 <file>");
            }
        }
    }

    class CountCommand implements Command {
        public String getName() { return "count"; }
        public String getDescription() { return "Count lines/words in file"; }
        public void execute(String[] args) {
            if (args.length > 0) {
                try {
                    long lines = Files.lines(Paths.get(args[0])).count();
                    String content = new String(Files.readAllBytes(Paths.get(args[0])));
                    int words = content.split("\\s+").length;

                    System.out.println("Lines: " + lines);
                    System.out.println("Words: " + words);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Usage: count <file>");
            }
        }
    }

    class BackupCommand implements Command {
        public String getName() { return "backup"; }
        public String getDescription() { return "Create backup copy of file"; }
        public void execute(String[] args) {
            if (args.length > 0) {
                try {
                    File source = new File(args[0]);
                    if (!source.exists()) {
                        System.out.println("File not found: " + args[0]);
                        return;
                    }

                    String backupName = args[0] + ".backup";
                    Files.copy(source.toPath(), Paths.get(backupName), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Backup created: " + backupName);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Usage: backup <file>");
            }
        }
    }
}