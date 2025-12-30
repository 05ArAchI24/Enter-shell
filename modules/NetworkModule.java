import java.util.Map;
import java.util.HashMap;
import java.net.InetAddress;
import java.util.Random;

public class NetworkModule implements Module {
    public String getName() { return "NetworkModule"; }
    public String getVersion() { return "1.0"; }
    public String getAuthor() { return "arach"; }

    public void onLoad() { System.out.println("🌐 NetworkModule loaded!"); }
    public void onUnload() { System.out.println("🌐 NetworkModule unloaded!"); }

    public Map<String, Command> getCommands() {
        Map<String, Command> cmds = new HashMap<>();
        cmds.put("ip", new IPCommand());
        cmds.put("ping", new PingCommand());
        cmds.put("ports", new PortsCommand());
        return cmds;
    }

    class IPCommand implements Command {
        public String getName() { return "ip"; }
        public String getDescription() { return "Show local IP address"; }
        public void execute(String[] args) {
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                System.out.println("Local IP: " + localhost.getHostAddress());
            } catch (Exception e) {
                System.out.println("Cannot get IP address");
            }
        }
    }

    class PingCommand implements Command {
        public String getName() { return "ping"; }
        public String getDescription() { return "Ping host (simulated)"; }
        public void execute(String[] args) {
            if (args.length > 0) {
                System.out.println("Pinging " + args[0] + " (simulated)...");
                System.out.println("Reply from " + args[0] + ": time=5ms");
                System.out.println("Reply from " + args[0] + ": time=3ms");
                System.out.println("Reply from " + args[0] + ": time=7ms");
            } else {
                System.out.println("Usage: ping <host>");
            }
        }
    }

    class PortsCommand implements Command {
        public String getName() { return "ports"; }
        public String getDescription() { return "Check common ports"; }
        public void execute(String[] args) {
            System.out.println("Common ports status:");
            System.out.println("Port 22 (SSH): OPEN");
            System.out.println("Port 80 (HTTP): OPEN");
            System.out.println("Port 443 (HTTPS): OPEN");
            System.out.println("Port 8080: CLOSED");
        }
    }
}