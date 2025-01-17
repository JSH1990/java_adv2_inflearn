package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6 {
    private List<SessionV6> sessions = new ArrayList<>();

    //동시에 실행되는걸 방지하고자 synchronized 설정
    //클라이언트의 새로운 연결을 통해, 새로운 세션이 새로 만들어지는 경우 add()를 호출해 세션 매니저에 세션을 추가한다.
    public synchronized void add(SessionV6 session){
        sessions.add(session);
    }

    //클라이언트의 연결이 끊어지면, 세션도 함께 정리된다. 이 경우 remove()를 호출해서 세션 매니저에서 세션을 제거한다.
    public synchronized void remove(SessionV6 session){
        sessions.remove(session);
    }

    //서버가 종료되면 ShutdownHook 발동해서, closeAll 해준다.
    //서버를 종료할때 사용하는 세션들도 모두 닫고 정리한다.
    public synchronized void closeAll(){
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }
}
