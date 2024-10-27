package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67, 68, 69};
        fos.write(input); //byte[]에 원하는 데이터를 담고 write()에 전달하면 해당 데이터를 한번에 출력할 수 있다.
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] buffer = new byte[10];
        int readCount = fis.read(buffer, 1, 9); //byte[]을 미리 만들어두고, 만들어둔 byte[] 에 한번에 데이터를 읽어 올수 있다.
        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(buffer));
        fis.close();
    }
}
