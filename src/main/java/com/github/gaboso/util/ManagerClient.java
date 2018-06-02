package com.github.gaboso.util;

import com.github.gaboso.constant.Textual;
import com.github.gaboso.dao.ClienteDAO;
import com.github.gaboso.model.Cliente;
import com.github.gaboso.helper.ScreenHelper;

import javax.swing.*;

/**
 * Classe com métodos auxiliares ao uso da classe Client
 */
public class ManagerClient extends ScreenHelper{

    private Cliente createClient(String address, String name, String phone, String cpf, char sex) {
        Cliente client = new Cliente();

        client.setNome(name);
        client.setEndereco(address);
        client.setTelefone(phone);
        client.setCpf(cpf);
        client.setSexo(sex);

        return client;
    }

    public boolean recordClient(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();

        if (clientDAO.save(client) != null) {
            showInformationMessage(frame, "efetuado");
            return true;
        } else {
            showMessageError(frame, Textual.CPF_JA_CADASTRADO);
            return false;
        }
    }

    public boolean updateClient(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();

        if (clientDAO.update(client) != null) {
            showInformationMessage(frame, "atualizado");
            return true;
        } else {
            showMessageError(frame, Textual.IMPOSSIVEL_ATUALIZAR);
            return false;
        }
    }

    public boolean removeClient(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();
        // Se ocorreu tudo bem e foi cadastrado
        if (clientDAO.remove(client)) {
            showInformationMessage(frame, "removido");
            return true;
        } else {
            showMessageError(frame, Textual.IMPOSSIVEL_REMOVER);
            return false;
        }
    }

    public Cliente createValidateClient(String name, String address, String cpf, String phone,
                                        JRadioButton radioButtonFemale, JRadioButton radioButtonMale) {
        char sex = ' ';
        Validator validator = new Validator();
        boolean error = validator.checkAll(address, name, cpf, phone, radioButtonMale, radioButtonFemale);

        if (!error) {
            if (radioButtonFemale.isSelected())
                sex = 'f';
            else if (radioButtonMale.isSelected())
                sex = 'm';

            return createClient(address, name, phone, cpf, sex);
        } else
            return null;
    }

}