package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileManager {

    public String readFile(String filePath) throws CipherException {

        try {
            Path path = checkPath(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new CipherException("Не удалось прочитать файл: " + filePath, ex);
        }
    }

    public void writeFile(String content, String filePath) throws CipherException {

            Path path = checkPath(filePath).toAbsolutePath();
            try {
                byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
                Files.write(path, bytes);
            } catch (IOException ex) {
                throw new CipherException("Не удалось записать файл " + filePath, ex);
            }
    }

    public void checkIfPathIsDifferent(String sourcePath, String destPath) throws CipherException {
            Path source = checkPath(sourcePath);
            Path destination = checkPath(destPath);
            try {
                if (Files.exists(source) && Files.exists(destination) && Files.isSameFile(source, destination)) {
                    throw new CipherException("Результат нельзя записать в исходный файл");
                }
            } catch (IOException ex) {
                throw new CipherException("Не удалось проверить файл " + destination, ex);
            }
    }


    private Path checkPath(String filePath) throws CipherException {
            if (filePath.isEmpty()) {
                throw new CipherException("Где путь к файлу?");
            }
            try {
                return Path.of(filePath);
            } catch (InvalidPathException ex) {
                throw new CipherException("Недопустимый путь: " + filePath, ex);
            }
    }
}
