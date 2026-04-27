package org.CodeSignal;

import java.util.ArrayList;
import java.util.List;

public class NewsAlignment {

    public static String[] solution(String[][] paragraphs, String[] aligns, int width) {

        List<String> lines = new ArrayList<>();

        // Top border
        lines.add("*".repeat(width + 2));

        // Process each paragraph
        for (int p = 0; p < paragraphs.length; p++) {
            String[] words = paragraphs[p];
            String align = aligns[p];

            int i = 0;
            while (i < words.length) {

                StringBuilder line = new StringBuilder(words[i]);
                i++;

                // Try to add as many words as possible
                while (i < words.length) {
                    if (line.length() + 1 + words[i].length() > width) {
                        break;
                    }
                    line.append(" ").append(words[i]);
                    i++;
                }

                int extraSpaces = width - line.length();

                // Align text
                if (align.equals("CENTER")) {
                    line.append(" ".repeat(extraSpaces));
                } else { // RIGHT
                    line.insert(0, " ".repeat(extraSpaces));
                }

                // Add border
                lines.add("*" + line + "*");
            }
        }

        // Bottom border
        lines.add("*".repeat(width + 2));

        return lines.toArray(new String[0]);
    }

    // Example test
    public static void main(String[] args) {
        String[][] paragraphs = {
                {"hello", "world"},
                {"How", "areYou", "doing"},
                {"Please look", "and align", "to right"}
        };

        String[] aligns = {"LEFT", "RIGHT", "CENTER"};
        int width = 16;

        String[] result = solution(paragraphs, aligns, width);

        for (String line : result) {
            System.out.println(line);
        }
    }
}
