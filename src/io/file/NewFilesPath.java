package io.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewFilesPath {
    private static final String PATH = "temp/hello2.txt";
    public static void main(String[] args) throws IOException {

        String writeString = "ABC\n가나다";
        System.out.println("== Write String ==");
        System.out.println("writeString = " + writeString);

        Path path = Path.of(PATH);

        //파일에 쓰기
        Files.writeString(path, writeString, StandardCharsets.UTF_8);
        //파일에 읽기
        String readString = Files.readString(path, StandardCharsets.UTF_8);

        System.out.println("readString = " + readString);
    }
}
