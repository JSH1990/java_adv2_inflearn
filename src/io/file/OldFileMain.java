package io.file;

import java.io.File;
import java.io.IOException;

public class OldFileMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temple/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("file.exists() = " + file.exists());

        boolean created = file.createNewFile();
        System.out.println("created = " + created);

        boolean dirCreated = directory.mkdir();
        System.out.println("dirCreated = " + dirCreated);

        //boolean deleted = file.delete();
        //System.out.println("deleted = " + deleted);

        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile);

    }
}
