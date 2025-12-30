import java.util.Map;
import java.util.HashMap;
import java.util.TimeZone;

public class SystemInfoModule implements Module {
    public String getName() { return "SystemInfoModule"; }
    public String getVersion() { return "1.0"; }
    public String getAuthor() { return "arach"; }

    public void onLoad() { System.out.println("💻 SystemInfoModule loaded!"); }
    public void onUnload() { System.out.println("💻 SystemInfoModule unloaded!"); }

    public Map<String, Command> getCommands() {
        Map<String, Command> cmds = new HashMap<>();
        cmds.put("sysinfo", new SysInfoCommand());
        cmds.put("javaver", new JavaVerCommand());
        cmds.put("timezone", new TimezoneCommand());
        return cmds;
    }

    class SysInfoCommand implements Command {
        public String getName() { return "sysinfo"; }
        public String getDescription() { return "Show system information"; }
        public void execute(String[] args) {
            System.out.println("OS: " + System.getProperty("os.name"));
            System.out.println("Arch: " + System.getProperty("os.arch"));
            System.out.println("Cores: " + Runtime.getRuntime().availableProcessors());
            System.out.println("User: " + System.getProperty("user.name"));
            System.out.println("Home: " + System.getProperty("user.home"));
        }
    }

    class JavaVerCommand implements Command {
        public String getName() { return "javaver"; }
        public String getDescription() { return "Show Java version"; }
        public void execute(String[] args) {
            System.out.println("Java Version: " + System.getProperty("java.version"));
            System.out.println("JVM Vendor: " + System.getProperty("java.vendor"));
            System.out.println("JVM Name: " + System.getProperty("java.vm.name"));
        }
    }

    class TimezoneCommand implements Command {
        public String getName() { return "timezone"; }
        public String getDescription() { return "Show timezone info"; }
        public void execute(String[] args) {
            TimeZone tz = TimeZone.getDefault();
            System.out.println("Timezone: " + tz.getID());
            System.out.println("Display Name: " + tz.getDisplayName());
            System.out.println("DST: " + (tz.useDaylightTime() ? "Yes" : "No"));
        }
    }
}