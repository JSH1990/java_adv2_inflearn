package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV4 {

        private static final int PORT = 12345;
        public static void main(String[] args) throws IOException {
            log("클라이언트 시작");

            //finally 블록에서 변수에 접근해야한다. 따라서 try 블록안에서 선언할 수 없다.
            Socket socket = null; //Socket은 서버와 데이터를 주고받기 위한 스트림을 제공한다.
            DataInputStream input = null; //서버에서 전달한 데이터를 클라이언트가 받을때 사용한다.
            DataOutputStream output = null; //클라이언트에서 서버에 데이터를 전달할때 사용한다.

            try {
                socket = new Socket("localhost", PORT);
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());

                //InputStream, OutputStream을 그대로 사용하면 모든 데이터를 byte로 변환해서 전달해야하기때문에 번거롭다. 여기서는 DataInputStream, DataOutputStream이라는 보조스트림을 사용하여, 자바 타입의 메세지를 편리하게 주고받을 수 있다.

                log("소켓 연결: " + socket);

                Scanner scanner = new Scanner(System.in);

                while (true){
                    System.out.print("전송 문자: ");
                    String toSend = scanner.nextLine();

                    //서버에게 문자보내기
                    output.writeUTF(toSend);
                    log("client -> server: " + toSend);

                    if(toSend.equals("exit")){
                        break;
                    }

                    //서버로부터 문자 받기
                    String received = input.readUTF();
                    log("client <- server: " + received);
                }
            } catch (IOException e) {
                log(e);
            }finally {
                closeAll(socket, input, output);
                log("연결 종료: " + socket);
            }
        }
    }

