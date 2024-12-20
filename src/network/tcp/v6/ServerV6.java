package network.tcp.v6;

import network.tcp.v5.SessionV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {

    private static final int PORT = 12345; //반드시 지정해야한다.

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT); //서버는 서버 소켓이라는 특별한 소켓을 사용한다.
        //지정한 포트를 사용해서 서버소켓을 생성하면, 클라이언트는 해당 포트로 서버를 연결할수있다.
        log("서버 소켓 시작 - 리스닝 포트: "+ PORT);

        //ShutdownHook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

        try {
            while (true){
                Socket socket = serverSocket.accept(); //블로킹
                log("소켓 연결: " + socket);

                SessionV6 session = new SessionV6(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        }catch (IOException e){
            log("서버 소켓 종료: " + e);
        }
    }

    //자바프로세스가 종료될때, 셧다운스레드가 실행된다.
    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000); //자원정리 대기
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }

}
