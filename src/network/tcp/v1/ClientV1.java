package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class ClientV1 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = new Socket("localhost", PORT); //Socket은 서버와 데이터를 주고받기 위한 스트림을 제공한다.
        DataInputStream input = new DataInputStream(socket.getInputStream()); //서버에서 전달한 데이터를 클라이언트가 받을때 사용한다.
        DataOutputStream output = new DataOutputStream(socket.getOutputStream()); //클라이언트에서 서버에 데이터를 전달할때 사용한다.

        //InputStream, OutputStream을 그대로 사용하면 모든 데이터를 byte로 변환해서 전달해야하기때문에 번거롭다. 여기서는 DataInputStream, DataOutputStream이라는 보조스트림을 사용하여, 자바 타입의 메세지를 편리하게 주고받을 수 있다.
        
        log("소켓 연결: " + socket);
        
        //서버에서 문자보내기
        String toSend = "Hello";
        output.writeUTF(toSend);
        log("client -> server: " + toSend);
        
        //서버로부터 문자 받기
        String received = input.readUTF();
        log("client <- server: " + received);

        //자원정리
        log("연결 종료 : " + socket);
        input.close();
        output.close();
        socket.close();

    }
}
