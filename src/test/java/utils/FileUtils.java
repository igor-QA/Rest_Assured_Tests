package utils;

import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/** Вспомогательный класс для работы с файлами */
public class FileUtils {
    public static String readFromFile(String path) {
        try (FileInputStream fis = new FileInputStream(new File(path))) {
            return IOUtils.toString(fis, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}