package io.file.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FilesCopyMainV3 {
    public static void main(String[] args) throws IOException {
        Path source = Path.of("temp/copy.dat");
        Path target = Path.of("temp/copy_new.dat");
        Files.copy(source,target, StandardCopyOption.REPLACE_EXISTING);

    }
}
