package com.javarush.caesar.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Cipher {

    private final char[] alphabet;
    private final Map<Character, Integer> charToIndex = new HashMap<>();

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;

        for (int i = 0; i < alphabet.length; i++) {
            charToIndex.put(alphabet[i], i);
        }
    }

    public String encrypt(String text, int shift) {

        StringBuilder stringBuilder = new StringBuilder();
        int charIndex;
        text = text.toLowerCase(Locale.ROOT);

        for (int i = 0; i < text.length(); i++) {

            if (charToIndex.containsKey(text.charAt(i))) {
                charIndex  = charToIndex.get(text.charAt(i));
                stringBuilder.append(alphabet[(charIndex+shift) % alphabet.length]);

            }
            else  {
                stringBuilder.append(text.charAt(i));
            }
        }

        return stringBuilder.toString();

    }

    public String decrypt(String encryptedText, int shift) {

        StringBuilder stringBuilder = new StringBuilder();
        int charIndex;
        encryptedText = encryptedText.toLowerCase(Locale.ROOT);
        for (int i = 0; i < encryptedText.length(); i++) {

            if (charToIndex.containsKey(encryptedText.charAt(i))) {
                charIndex  = charToIndex.get(encryptedText.charAt(i));
                stringBuilder.append(alphabet[(charIndex - shift + alphabet.length) % alphabet.length]);
            }
            else  {
                stringBuilder.append(encryptedText.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}




