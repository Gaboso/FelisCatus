package br.edu.ifms.lp3.util;

import javax.swing.*;

/**
 * Classe com métodos úteis para realizar validações
 */
public class Validator {

    public boolean checkCPF(String cpf) {
        // se CPF estiver vazio
        if (cpf.equals("   .   .   -  ")) {
            return false;
        } else {
            ValidateCPF validateCPF = new ValidateCPF();
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            // Se o CPF for verdadeiro
            return validateCPF.isCPF(cpf);
        }
    }

    public boolean checkName(String name) {
        // Se nome estiver vazio e se nome tiver menos que 70 caracteres
        return !name.isEmpty() && name.trim().length() < 70;
    }

    public boolean checkAddress(String address) {
        // Se endereço estiver vazio
        if (address.isEmpty()) {
            return false;
        } else {
            String addressLowerCase = address.toLowerCase();

            return addressLowerCase.startsWith("rua ") || addressLowerCase.startsWith("avenida ")
                    || addressLowerCase.startsWith("alameda ") || addressLowerCase.startsWith("av. ")
                    || addressLowerCase.startsWith("beco ") || addressLowerCase.startsWith("viela ")
                    || addressLowerCase.startsWith("praça ") || addressLowerCase.startsWith("r. ");
        }
    }

    public boolean checkPhone(String phone) {
        // Se telefone estiver vazio
        return !(phone.equals("(  )     -    ") || phone.equals("(11) 1111-1111")
                || phone.equals("(22) 2222-2222") || phone.equals("(33) 3333-3333")
                || phone.equals("(44) 4444-4444") || phone.equals("(55) 5555-5555")
                || phone.equals("(66) 6666-6666") || phone.equals("(77) 7777-7777")
                || phone.equals("(88) 8888-8888") || phone.equals("(99) 9999-9999"));
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