package com.sousa.ronny.cozinheja.Formaters;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FormatadorString {

    public static String semAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}

