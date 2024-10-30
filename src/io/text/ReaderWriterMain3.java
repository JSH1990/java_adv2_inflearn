package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;

public class ReaderWriterMain3 {
    public static void main(String[] args) throws IOException {
        String writeString = "abc";

        FileWriter fw = new FileWriter(FILE_NAME, StandardCharsets.UTF_8);
        fw.write(writeString);
        fw.close();

        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, StandardCharsets.UTF_8);

        int ch;
        while ((ch = fr.read()) != -1){
            content.append((char) ch);
        }
        fr.close();
    }
}
