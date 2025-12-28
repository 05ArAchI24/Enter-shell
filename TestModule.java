import java.util.*;

public class TestModule implements Module {
    public String getName() { return "TestModule"; }
    public String getVersion() { return "1.0"; }
    public String getAuthor() { return "arach"; }

    public void onLoad() {
        System.out.println("🎮 TestModule loaded successfully!");
    }

    public void onUnload() {
        System.out.println("👋 TestModule unloaded");
    }

    public Map<String, Command> getCommands() {
        Map<String, Command> cmds = new HashMap<>();
        cmds.put("test", new TestCommand());
        cmds.put("greet", new GreetCommand());
        cmds.put("calc", new CalcCommand());
        return cmds;
    }

    class TestCommand implements Command {
        public String getName() { return "test"; }
        public String getDescription() { return "Test if module works"; }
        public void execute(String[] args) {
            System.out.println("✅ Module system is working!");
        }
    }

    class GreetCommand implements Command {
        public String getName() { return "greet"; }
        public String getDescription() { return "Greet someone"; }
        public void execute(String[] args) {
            if (args.length > 0) {
                System.out.println("👋 Hello, " + args[0] + "!");
            } else {
                System.out.println("👋 Hello, friend!");
            }
        }
    }

    class CalcCommand implements Command {
        public String getName() { return "calc"; }
        public String getDescription() { return "Calculate a + b"; }
        public void execute(String[] args) {
            if (args.length == 2) {
                try {
                    int a = Integer.parseInt(args[0]);
                    int b = Integer.parseInt(args[1]);
                    System.out.println(a + " + " + b + " = " + (a + b));
                } catch (Exception e) {
                    System.out.println("❌ Usage: calc <number> <number>");
                }
            } else {
                System.out.println("❌ Usage: calc <number> <number>");
            }
        }
    }
}