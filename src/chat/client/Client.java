package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

/*
- 클라이언트 전반을 관리하는 클래스이다.
- Socket, ReadHandler, WriteHandler 를 모두 생성하고 관리한다.
- close() 메서드를 통해 전체자원으르 정리하는 기능도 제공한다.

 */
public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private ReadHandler readHandler;
    private WriteHandler writeHandler;
    private boolean closed = false;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        log("클라이언트 시작");
        socket = new Socket(host, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        readHandler = new ReadHandler(input, this);
        writeHandler = new WriteHandler(output, this);
        Thread readThread = new Thread(readHandler, "readHandler");
        Thread writeThread = new Thread(writeHandler, "writeHandler");
        readThread.start();
        writeThread.start();

    }

    //ReadHandler,WriteHandler 가 동시에 Client.close() 호출해도 synchronized 때문에 동시 호출되지않는다.
    public synchronized void close() {
        if(closed){
            return;
        }
        writeHandler.close();
        readHandler.close();
        closeAll(socket, input, output);
        log("연결 종료: " + socket);
        closed = true;
        log("연결 종료 : " + socket);

    }
}