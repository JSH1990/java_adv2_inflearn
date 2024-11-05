package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewFilesMain {
    public static void main(String[] args) {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        try {
            Files.createFile(file);
            Files.createFile(file);
        } catch (FileAlreadyExistsException e){
            System.out.println(file + " 파일 이미 있어요");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
