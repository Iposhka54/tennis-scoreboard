package com.iposhka.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NameFormatter {
    public static String formatName(String name){
        if (name == null || name.isEmpty()) {
            return name;
        }

        name = name.toLowerCase();

        StringBuilder builder = new StringBuilder();

        String[] words = name.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                builder.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return builder.toString().trim();
    }
}
