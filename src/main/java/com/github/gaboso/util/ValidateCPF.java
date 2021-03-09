package com.github.gaboso.util;

public class ValidateCPF {

    public boolean isCPF(String cpf) {

        if (cpf.length() != 11 || Validator.digitsAreEquals(cpf, 11)) {
            return false;
        } else {
            int i;

            ValidateCPF validateCPF = new ValidateCPF();

            int sum = 0;
            int weight = 10;
            for (i = 0; i < 9; i++) {
                sum = validateCPF.runCalculation(sum, weight, i, cpf);
                weight--;
            }

            int r = 11 - (sum % 11);
            char tenDigit = validateCPF.verifyR(r);

            sum = 0;
            weight = 11;

            for (i = 0; i < 10; i++) {
                sum = validateCPF.runCalculation(sum, weight, i, cpf);
                weight--;
            }

            r = 11 - (sum % 11);
            char elevenDigit = validateCPF.verifyR(r);

            return (tenDigit == cpf.charAt(9)) && (elevenDigit == cpf.charAt(10));
        }
    }

    private int runCalculation(int sum, int weight, int index, String cpf) {
        int number = cpf.charAt(index) - 48;
        return sum + (number * weight);
    }

    private char verifyR(int r) {
        return (r == 10) || (r == 11) ? '0' : (char) (r + 48);
    }

}