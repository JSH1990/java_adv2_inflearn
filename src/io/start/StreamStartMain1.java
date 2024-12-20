package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close(); //자바 외부의 파일을 사용할때는 항상 close 해야한다.

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        System.out.println("fis.read() = " + fis.read());
        System.out.println("fis.read() = " + fis.read());
        System.out.println("fis.read() = " + fis.read());

    }
}
