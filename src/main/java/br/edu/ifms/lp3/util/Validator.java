package br.edu.ifms.lp3.util;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe com métodos úteis para realizar validações
 */
public class Validator {

    public boolean checkCPF(String cpf) {
        // se CPF estiver vazio
        if ("   .   .   -  ".equals(cpf)) {
            return false;
        } else {
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
     * @param minimumOccurrences - Quantidade minima de ocorrencia de caracteres para considerar que os caracteres são iguais
     * @return Retorna true caso um digito se repita na quantidade informada no minimumOccurrences, caso contrario retorna false;
     */
    public boolean digitsAreEquals(String text, int minimumOccurrences) {

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
     * Verifica se o nome não esta vazio e possui até 70 caracteres
     *
     * @param name - Nome a ser checado
     * @return retorna true caso obedeça as condições abaixo
     */
    public boolean checkName(String name) {
        // Se nome estiver vazio e se nome tiver menos que 70 caracteres
        return !name.isEmpty() && name.trim().length() < 70;
    }

    public boolean checkAddress(String address) {
        // Se endereço estiver vazio
        if (address.isEmpty()) {
            return false;
        } else {
            String addressLC = address.toLowerCase();
            //TODO corrigir condições
            boolean part1 = addressLC.startsWith("rua ") || addressLC.startsWith("avenida ") || addressLC.startsWith("alameda ");
            boolean part2 = addressLC.startsWith("av. ") || addressLC.startsWith("beco ") || addressLC.startsWith("viela ");
            boolean part3 = addressLC.startsWith("praça ") || addressLC.startsWith("r. ");

            return part1 || part2 || part3;
        }
    }

    public boolean checkPhone(String phone) {
        if (phone.length() != 14 || digitsAreEquals(phone, 10)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("\\(\\d{2,2}\\) \\d{4,4}-\\d{4,4}");
            Matcher matcher = pattern.matcher(phone);

            // Se não for igual a nenhuma da condições acima
            return matcher.find();
        }
    }

    /**
     * Método que ira verificar se os sexo foi selecionado
     *
     * @return retorna true se algum botão foi selecionado, e falso se contrario
     */
    private boolean checkRadioButton(JRadioButton male, JRadioButton female) {
        return female.isSelected() || male.isSelected();
    }

    /**
     * Método que verifica se todos os requisitos foram preenchidos, se foram
     * preenchidos corretamente retora false, caso contrario retorna true.<br>
     * <p>
     * <br>
     * erro = false -> OK<br>
     * erro = true -> !OK
     *
     * @param address - Endereço que foi digitado na tela
     * @param name    - Nome que foi digitado na tela
     * @param cpf     - CPF que foi digitado na tela
     * @param phone   - Telefone que foi digitado na tela
     * @param male    - Botão do sexo masculino
     * @param female  - Botão do sexo feminino
     * @return retorna true se possuir algum erro e false se não houver nenhum
     */
    boolean checkAll(String address, String name, String cpf, String phone, JRadioButton male, JRadioButton female) {
        return !checkAddress(address) || !checkName(name) || !checkCPF(cpf)
                || !checkPhone(phone) || !checkRadioButton(male, female);
    }

}