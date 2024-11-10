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
        ServerSocket serverSocket = new ServerSocket(PORT); //서버는 서버 소켓이라는 특별한 소켓을 사용한다.
        //지정한 포트를 사용해서 서버소켓을 생성하면, 클라이언트는 해당 포트로 서버를 연결할수있다.
        log("서버 소켓 시작 - 리스닝 포트: "+ PORT);

        while (true){
            Socket socket = serverSocket.accept(); //블로킹
            log("소켓 연결: " + socket);

            SessionV5 session = new SessionV5(socket);
            Thread thread = new Thread(session); 
            thread.start();
        }
    }

}
