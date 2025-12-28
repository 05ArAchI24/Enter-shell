import java.util.Map;

public interface Module {
    String getName();
    String getVersion();
    String getAuthor();
    void onLoad();
    void onUnload();
    Map<String, Command> getCommands();
}