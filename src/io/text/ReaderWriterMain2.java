package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;

public class ReaderWriterMain2 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        //문자를 입력받고, 받은문자를 인코딩해서 byte[]로 변환한다. 65,66,67
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        osw.write(writeString);
        osw.close();

        //파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

        StringBuilder content = new StringBuilder();

        int ch;
        while ((ch = isr.read()) != -1) {
        content.append((char)ch);

        }
        isr.close();
        System.out.println("read String: " + content);
    }
}
