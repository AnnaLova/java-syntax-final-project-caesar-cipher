package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
            return key >= 0 && key < alphabet.length;

    }

    public boolean isFileExists(String filePath) throws CipherException {
        // TODO проверить, что путь существует и ведет к файлу (папка не подходит)
        Path path;
        try {
            path = Path.of(filePath);
        } catch (InvalidPathException ex) {
            throw new CipherException("Недопустимый путь: " + filePath, ex);
        }
        if (Files.isRegularFile(path)){
            return true;
        }
        return false;
    }
}
