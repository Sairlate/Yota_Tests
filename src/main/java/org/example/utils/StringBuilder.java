package org.example.utils;

import java.util.Random;

public class StringBuilder {
    private static final String latLC = "abcdefghijklmnopqrstuvwxyz";
    private static final String latUC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String cyrLC = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String cyrUC = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String dig = "0123456789";

    public static String genLatin(int length){
        String characters = latLC + latUC;
        return generateRandomString(characters, length);
    }

    public static String genCyrillic(int length){
        String characters = cyrLC + cyrUC;
        return generateRandomString(characters, length);
    }

    public static int genNumeric(int length){
        return Integer.parseInt(generateRandomString(dig, length));
    }

    public static String genAlphabetic(int length){
        String characters = latLC + latUC + cyrLC + cyrUC;
        return generateRandomString(characters, length);
    }
    public static String genAlphanumeric(int length){
        String characters = latLC + latUC + cyrLC + cyrUC + dig;
        return generateRandomString(characters, length);
    }

    private static String generateRandomString(String characters, int length){
        Random random = new Random();
        java.lang.StringBuilder result = new java.lang.StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }

        return result.toString();

    }
}
