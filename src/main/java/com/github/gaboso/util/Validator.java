package com.github.gaboso.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;

public class Validator {

    private Validator() {
    }

    public static boolean all(String address, String name, String cpf, String phone, JRadioButton male, JRadioButton female) {
        boolean isRadioButtonIsSelected = female.isSelected() || male.isSelected();

        return !name(name)
            || !cpf(cpf)
            || !isRadioButtonIsSelected
            || !address(address)
            || !phone(phone);
    }

    public static boolean name(String name) {
        return !name.isEmpty() && name.trim().length() < 70;
    }

    public static boolean cpf(String cpf) {
        if ("   .   .   -  ".equals(cpf) || cpf.contains(" ")) {
            return false;
        }

        String cpfNoMask = cpf.replace(".", "")
                              .replace("-", "");

        return ValidateCPF.isCPF(cpfNoMask);
    }

    public static boolean address(String address) {
        if (!address.isEmpty()) {
            String addressLC = address.toLowerCase();
            List<String> patterns = Arrays.asList("rua", "avenida", "alameda", "av.", "beco", "viela", "praça", "r.");
            return patterns.stream()
                           .anyMatch(pattern -> addressLC.startsWith(pattern + " "));
        }
        return false;
    }

    public static boolean phone(String phone) {
        if (phone.length() != 14 || digitsAreEquals(phone, 10)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("\\(\\d{2}\\) \\d{4}-\\d{4}");
            return pattern.matcher(phone)
                          .find();
        }
    }

    public static boolean digitsAreEquals(String text, int minimumOccurrences) {
        for (int i = 0; i < text.length(); i++) {
            int occurrences = 0;

            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(i) == text.charAt(j)) {
                    occurrences++;
                }
            }

            if (occurrences == minimumOccurrences) {
                return true;
            }
        }

        return false;
    }

}