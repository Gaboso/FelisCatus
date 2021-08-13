package com.github.gaboso.util;

public class ValidateCPF {

    private ValidateCPF() {
    }

    public static boolean isCPF(String cpf) {
        if (cpf.length() != 11 || Validator.digitsAreEquals(cpf, 11)) {
            return false;
        }

        int index;

        int sum = 0;
        int weight = 10;
        for (index = 0; index < 9; index++) {
            sum = runCalculation(sum, weight, index, cpf);
            weight--;
        }

        int r = 11 - (sum % 11);
        char tenDigit = verifyR(r);

        sum = 0;
        weight = 11;

        for (index = 0; index < 10; index++) {
            sum = runCalculation(sum, weight, index, cpf);
            weight--;
        }

        r = 11 - (sum % 11);
        char elevenDigit = verifyR(r);

        return (tenDigit == cpf.charAt(9)) && (elevenDigit == cpf.charAt(10));
    }

    private static int runCalculation(int sum, int weight, int index, String cpf) {
        int number = cpf.charAt(index) - 48;
        return sum + (number * weight);
    }

    private static char verifyR(int r) {
        return (r == 10) || (r == 11) ? '0' : (char) (r + 48);
    }

}