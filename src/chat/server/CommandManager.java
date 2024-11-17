package chat.server;

import java.io.IOException;

/*
-클라이언트에게 전달받은 메세지를 처리하는 인터페이스이다.
= 인터페이스를 사용한 이유는 향후 구현체를 점진적으로 변경하기 위해서 이다.

- totalMessage : 클라이언트에게 전달받은 메세지
- Session session 현재 세션
 */
public interface CommandManager {
    void execute(String totalMessage, Session session) throws IOException;
}
