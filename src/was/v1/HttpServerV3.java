package was.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

/*
스레드를 생성하고 관리해준다.
 */
public class HttpServerV3 {
    //스레드풀을 사용한다. 여기서는 newFixedThreadPool(10)을 사용해서 최대 동시에 10개의 스레드를 사용할수있게했다.
    //결과적으로 10개의 요청을 동시에 처리할수있다.
    private final ExecutorService es = Executors.newFixedThreadPool(10);
    private final int port;
    public HttpServerV3(int port) {
        this.port = port;
    }
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 port: " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            es.submit(new HttpRequestHandlerV3(socket));
        }
    }
}