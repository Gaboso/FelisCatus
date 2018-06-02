package com.github.gaboso.util;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
    }

    public static boolean cpf(String cpf) {
        if ("   .   .   -  ".equals(cpf))
            return false;
        else {
            ValidateCPF validateCPF = new ValidateCPF();
            String cpfNoMask = cpf.replace(".", "");
            cpfNoMask = cpfNoMask.replace("-", "");

            return validateCPF.isCPF(cpfNoMask);
        }
    }

    public static boolean name(String name) {
        return !name.isEmpty() && name.trim().length() < 70;
    }

    public static boolean address(String address) {
        if (address.isEmpty()) {
            return false;
        } else {
            String addressLC = address.toLowerCase();
            String[] patterns = {"rua", "avenida", "alameda", "av.", "beco", "viela", "praça", "r."};

            for (String pattern : patterns) {
                if (addressLC.startsWith(pattern + " "))
                    return true;
            }

            return false;
        }
    }

    public static boolean phone(String phone) {
        if (phone.length() != 14 || digitsAreEquals(phone, 10))
            return false;
        else {
            Pattern pattern = Pattern.compile("\\(\\d{2,2}\\) \\d{4,4}-\\d{4,4}");
            Matcher matcher = pattern.matcher(phone);
            return matcher.find();
        }
    }

    public static boolean all(String address, String name, String cpf, String phone, JRadioButton male, JRadioButton female) {
        boolean part1 = !name(name) || !cpf(cpf) || !radioButtonIsSelected(male, female);
        boolean part2 = !address(address) || !phone(phone);

        return part1 || part2;
    }

    public static boolean digitsAreEquals(String text, int minimumOccurrences) {
        for (int i = 0; i < text.length(); i++) {
            int occurrences = 0;

            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(i) == text.charAt(j))
                    occurrences++;
            }

            if (occurrences == minimumOccurrences) {
                return true;
            }
        }

        return false;
    }

    private static boolean radioButtonIsSelected(JRadioButton male, JRadioButton female) {
        return female.isSelected() || male.isSelected();
    }

}