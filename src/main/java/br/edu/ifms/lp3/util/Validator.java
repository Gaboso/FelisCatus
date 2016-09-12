package br.edu.ifms.lp3.util;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe com métodos úteis para realizar validações
 */
public class Validator {

    /**
     * Método para auxiliar na validação do CPF
     *
     * @param cpf - CPF a ser validado
     * @return Retorna true se o cpf for valido e false caso contrário
     */
    public boolean checkCPF(String cpf) {
        // se CPF estiver vazio
        if ("   .   .   -  ".equals(cpf))
            return false;
        else {
            ValidateCPF validateCPF = new ValidateCPF();
            String cpfNoMask = cpf.replace(".", "");
            cpfNoMask = cpfNoMask.replace("-", "");
            // Se o CPF for verdadeiro
            return validateCPF.isCPF(cpfNoMask);
        }
    }

    /**
     * Método para verificar se os dígitos são iguais
     *
     * @param text               - Texto a ser verificado
     * @param minimumOccurrences - Quantidade minima de ocorrência de caracteres para considerar que os caracteres são iguais
     * @return Retorna true se  um digito se repita na quantidade informada no minimumOccurrences e false caso contrário
     */
    boolean digitsAreEquals(String text, int minimumOccurrences) {

        for (int i = 0; i < text.length(); i++) {
            int occurrences = 0;

            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(i) == text.charAt(j))
                    occurrences++;
            }

            if (occurrences == minimumOccurrences)
                return true;
        }

        return false;
    }

    /**
     * Método para verificar se o nome não esta vazio e possui até 70 caracteres
     *
     * @param name - Nome a ser validado
     * @return Retorna true se o nome for valido e false caso contrário
     */
    public boolean checkName(String name) {
        // Se nome estiver vazio e se nome tiver menos que 70 caracteres
        return !name.isEmpty() && name.trim().length() < 70;
    }

    /**
     * Método para validar um endereço
     *
     * @param address - Endereço a ser validado
     * @return Retorna true se o endereço for valido e false caso contrário
     */
    public boolean checkAddress(String address) {
        // Se endereço estiver vazio
        if (address.isEmpty())
            return false;
        else {
            String addressLC = address.toLowerCase();
            String[] patterns = {"rua", "avenida", "alameda", "av.", "beco", "viela", "praça", "r."};

            for (String pattern : patterns) {
                if (addressLC.startsWith(pattern + " "))
                    return true;
            }

            return false;
        }
    }

    /**
     * Método para validar telefone
     *
     * @param phone - Telefone a ser validado
     * @return Retorna true se o telefone for valido e false caso contrário
     */
    public boolean checkPhone(String phone) {
        if (phone.length() != 14 || digitsAreEquals(phone, 10))
            return false;
        else {
            Pattern pattern = Pattern.compile("\\(\\d{2,2}\\) \\d{4,4}-\\d{4,4}");
            Matcher matcher = pattern.matcher(phone);
            return matcher.find();
        }
    }

    /**
     * Método que ira verificar se os sexo foi selecionado
     *
     * @param male   - RadioButton masculino
     * @param female - RadioButton feminino
     * @return Retorna true se algum radio button foi selecionado for valido e false caso contrário
     */
    private boolean checkRadioButton(JRadioButton male, JRadioButton female) {
        return female.isSelected() || male.isSelected();
    }

    /**
     * Método que verifica se todos os requisitos foram preenchidos, se foram
     * preenchidos corretamente retorna true, caso contrário retorna false.<br>
     *
     * @param address - Endereço que foi digitado na tela
     * @param name    - Nome que foi digitado na tela
     * @param cpf     - CPF que foi digitado na tela
     * @param phone   - Telefone que foi digitado na tela
     * @param male    - Botão do sexo masculino
     * @param female  - Botão do sexo feminino
     * @return Retorna true se possuir algum erro for valido e false caso contrário
     */
    boolean checkAll(String address, String name, String cpf, String phone, JRadioButton male, JRadioButton female) {
        boolean part1 = !checkName(name) || !checkCPF(cpf) || !checkRadioButton(male, female);
        boolean part2 = !checkAddress(address) || !checkPhone(phone);

        return part1 || part2;
    }

}