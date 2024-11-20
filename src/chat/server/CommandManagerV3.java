package chat.server;

import java.io.IOException;
import java.util.Map;

public class CommandManagerV3 implements CommandManager{

    private static final String DELIMITER = "\\|";
    private final SessionManager sessionManager;


    public CommandManagerV3(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


    @Override
    public void execute(String totalMessage, Session session) throws IOException {

    }
}
