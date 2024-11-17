package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.MyLogger.log;

/*
- WriteHandler 는 사용자 콘솔의 입력을 받아서 서버로 메세지를 전송한다.
- 처음 시작시 inputUsername() 통해 사용자의 이름을 입력받는다.
- 처음 채팅 서버에 접속하면 /join | {name} 을 전송한다. 이 메세지를 통해 입장했다는 정보와 사용자의 이름을 서버에 전달한다.
- 콘솔 입력시 /로 시작하면 /join, /exit 같은 특정 명령어를 수행한다고 가정한다.
- /를 입력하지않으면 일반 메세지로 보고, /message에 내용을 추가해서 서버에 전달한다.

- close()를 호출하면 System.in.close()를 통해 사용자의 콘솔 입력을 닫는다. 이렇게하면 Scanner를 통한 콘솔입력인
scanner.nextLine() 코드에서 대기하는 스레드에 다음 예외가 발생하면서, 대기 상태에서 빠져나올수 있다.

 */
public class WriteHandler implements Runnable{
    private static final String DELIMITER = "|"; //"|"를 사용하기위한 상수 만들어둠

    private final DataOutputStream output;
    private final Client client;
    private boolean closed = false;

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMITER + username);

            while (true){
                String toSend = scanner.nextLine(); //블로킹
                if(toSend.isEmpty()){
                    continue;
                }

                if(toSend.equals("/exit")){
                    output.writeUTF(toSend);
                    break; //while문 빠져나감
                }

                // "/"로 시작하면 명령어, 나머지는 일반 메세지
                if(toSend.startsWith("/")){
                    output.writeUTF(toSend);
                }else{
                    output.writeUTF("/message" + DELIMITER + toSend);
                }
            }

        } catch (IOException|NoSuchElementException e) {
            log(e);
        } finally {
            client.close();
        }

    }

    private static String inputUsername(Scanner scanner) {
        System.out.println("이름을 입력하세요.");
        String username;
        do {
            username = scanner.nextLine();
        }while (username.isEmpty());
        return username;
    }

    public synchronized void close(){
        if(closed){
            return;
        }

        try {
            System.in.close(); //Scanner 입력중지 (사용자의 입력을 닫음)
        } catch (IOException e) {
            log(e);
        }
        closed = true;
        log("WriteHandler 종료");
    }
}
