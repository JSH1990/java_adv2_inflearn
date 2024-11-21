package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV4 implements CommandManager{

    private static final String DELIMITER = "\\|";

    //명령어는 Map에 보관한다. 명령어 자체를 Key로 사용하고, 각 Key해당하는 Command 구현체를 저장해둔다.
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV4(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    /*
    명령어(key)를 처리할 Command 구현체를 Commands에서 찾아서 실행한다.
    예를 들어 /join 이라는 메세지가 들어왔다면 JoinCommand의 인스턴스가 반환된다.
    -Command를 찾았다면, 다형성을 활용해서 구현체의 excute()메서드를 호출한다.
    -만약 찾을수 없다면 처리할수 없는 명령어이다.
     */
    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        String key = args[0];

        //NullObject Pattern
        /*
        Map에는 getOrDefault라는 메서드가 존재한다.
        만약 key를 사용해서 찾을수 있다면 찾고, 찾을수 없다면 옆에있는 defaultObject를 반환한다.
        이 기능을 사용하면 null 을 받지않고, 항상 Command 객체를 받아서 처리할수있다.
        여기서는 key를 찾을수 없다면 DefaultCommand의 인스턴스를 반환한다.
         */
        Command command = commands.getOrDefault(key, defaultCommand);
        command.execute(args, session);
    }
}
