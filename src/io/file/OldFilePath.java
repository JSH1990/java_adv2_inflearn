package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("file.getPath() = " + file.getPath());

        //절대 경로
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

        //정규 경로
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());



        }
}
