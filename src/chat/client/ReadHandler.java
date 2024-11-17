package chat.client;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

/*
- ReadHandler는 Runnable 인터페이스를 구현하고, 별도의 스레드에서 실행한다.
- 서버의 메세지를 반복해서 받고, 콘솔에 출력하는 단순한 기능을 제공한다.
- 클라이언트 종료시 ReadHandler 종료된다. 중복 종료를 막기위해 동기화 코드와 closed 플래그룰 사용했다.
- IOException 예외가 발생하면 client.close()를 통해 클라이언트를 종료하고, 전체자원을 정리한다.
 */
public class ReadHandler implements Runnable{
    private final DataInputStream input;
    private final Client client;
    public boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true){
                String received = input.readUTF();
                System.out.println(received);
            }
        } catch (IOException e) {
            log(e);
        }finally {
            client.close();
        }
    }

    //자원정리
    public synchronized void close(){
        if(closed){
            return;
        }

        //종료 로직 필요시 작성
        closed = true;
        log("readHandelr 종료");
    }
}
