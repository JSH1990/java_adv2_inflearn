package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

//이때 OS계층에서 TCP 3 wayhandshake가 발생하고, TCP 연결이 완료된다.
//TCP 연결이 완료되면, 서버는 OS backlog queue라는 곳에 클라이언트와 서버의 TCP 연결 정보를 보관한다.

//TCP 연결시에는 클라이언트 서버 모두 IP, 포트 정보가 필요하다.

/*
생각해보면 클라이언트는 자신의 포트를 지정한 적이 없다.
서버의 경우 포트가 명확하게 지정되어 있어야한다. 그래야 클라이언트에서 서버에 어떤 포트에 접속할수 있다.

 */

public class ServerV1 {
    private static final int PORT = 12345; //반드시 지정해야한다.

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT); //서버는 서버 소켓이라는 특별한 소켓을 사용한다.
        //지정한 포트를 사용해서 서버소켓을 생성하면, 클라이언트는 해당 포트로 서버를 연결할수있다.
        log("서버 소켓 시작 - 리스닝 포트: "+ PORT);

        Socket socket = serverSocket.accept(); //서버 소켓은 단지 클라이언트와 서버의 TCP 연결만 지원하는 특별한 소켓이다.
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        //클라이언트로부터 문자 받기
        String received = input.readUTF();
        log("client -> server: " + received);

        //클라이언트에게 문자보내기
        String toSend = received + "World";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);

        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
