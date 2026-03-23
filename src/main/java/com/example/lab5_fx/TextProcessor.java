package com.example.lab5_fx;

public class TextProcessor {
    private static final int SHIFT = 3;

    public String caesarEncrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char ch = (char)(((c - 'A' + SHIFT) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(c)) {
                char ch = (char)(((c - 'a' + SHIFT) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}