package br.edu.ifms.lp3.util;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean checkCPF(String cpf) {
        if ("   .   .   -  ".equals(cpf))
            return false;
        else {
            ValidateCPF validateCPF = new ValidateCPF();
            String cpfNoMask = cpf.replace(".", "");
            cpfNoMask = cpfNoMask.replace("-", "");

            return validateCPF.isCPF(cpfNoMask);
        }
    }

    boolean digitsAreEquals(String text, int minimumOccurrences) {
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

    public boolean checkName(String name) {
        return !name.isEmpty() && name.trim().length() < 70;
    }

    public boolean checkAddress(String address) {
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

    public boolean checkPhone(String phone) {
        if (phone.length() != 14 || digitsAreEquals(phone, 10))
            return false;
        else {
            Pattern pattern = Pattern.compile("\\(\\d{2,2}\\) \\d{4,4}-\\d{4,4}");
            Matcher matcher = pattern.matcher(phone);
            return matcher.find();
        }
    }

    private boolean checkRadioButton(JRadioButton male, JRadioButton female) {
        return female.isSelected() || male.isSelected();
    }

    boolean checkAll(String address, String name, String cpf, String phone, JRadioButton male, JRadioButton female) {
        boolean part1 = !checkName(name) || !checkCPF(cpf) || !checkRadioButton(male, female);
        boolean part2 = !checkAddress(address) || !checkPhone(phone);

        return part1 || part2;
    }

}