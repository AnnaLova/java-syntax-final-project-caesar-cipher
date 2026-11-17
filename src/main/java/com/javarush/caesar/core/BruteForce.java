package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

import java.util.Arrays;

/**
 *
 * Класс расшифровывает файл методом brute force: перебирает все возможные ключи
 * и выбирает наиболее правдоподобный текст на русском языке,
 * основываясь на системе баллов.
 *
 */

public class BruteForce {

    private final String[] shortRussianWordsDict = {
            "и", "в", "не", "на", "но", "что",
            "с", "а", "к", "по", "за", "из", "от", "до", "со"
    };

    /**
     * Метод перебирает ключи и пробует расшифровать зашифрованный текст.
     *
     * @param encryptedText текст, который расшифровываем
     * @param alphabet алфавит, который использован для шифра и раскодировки
     * @return возвращает наиболее вероятный из расшифрованных текстов
     * @throws CipherException выбрасывается, если ни один ключ не подшел
     */

    public String decryptByBruteForce(String encryptedText, char[] alphabet)
            throws CipherException {

        Cipher cipher = new Cipher(alphabet);

        String bestText = null;
        int bestScore = -1;

        for (int i = 1; i < alphabet.length; i++) {

                String decodedText = cipher.decrypt(encryptedText, i);
                int score = calculateScore(decodedText);

                if (score > bestScore) {
                    bestScore = score;
                    bestText = decodedText;
                }
        }

        if (bestText == null) {
            throw new CipherException("Не удалось подобрать ключ для расшифровки");
        }

        return bestText;
    }

    /**
     * Считает score на основе набранных текстом баллов,
     * score обозначает, насколько текст напоминает текст на русском языке
     *
     * @param decodedText текст, который оцениваем
     * @return возвращает подсчитанный score
     */

    public int calculateScore(String decodedText) {
        int sum = 0;

        for (int i = 0; i < decodedText.length() - 1; i++) {
            if ((decodedText.charAt(i) == '.' || decodedText.charAt(i) == ',')
                    && decodedText.charAt(i + 1) == ' ') {
                sum++;
            }
        }

        String[] words = decodedText.split("\\s+");

        for (int i = 0; i < words.length; i++ ) {
            words[i] = words[i].replace(",", "").replace(".", "");

            if (words[i].length() >= 1 && words[i].length() <= 12) {
                sum++;
            }

            if (Arrays.asList(shortRussianWordsDict).contains(words[i])) {
                sum = sum + 2;
            }
        }

        return sum;
    }
}
