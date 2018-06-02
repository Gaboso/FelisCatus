package com.github.gaboso.util;

import com.github.gaboso.constant.Textual;
import com.github.gaboso.dao.ClienteDAO;
import com.github.gaboso.helper.ScreenHelper;
import com.github.gaboso.model.Cliente;

import javax.swing.*;

/**
 * Classe com métodos auxiliares ao uso da classe Client
 */
public class ManagerClient extends ScreenHelper {

    public boolean save(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();

        if (clientDAO.save(client) != null) {
            showInfoMessage(frame, "efetuado");
            return true;
        } else {
            showErrorMessage(frame, Textual.CPF_JA_CADASTRADO);
            return false;
        }
    }

    public boolean update(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();

        if (clientDAO.update(client) != null) {
            showInfoMessage(frame, "atualizado");
            return true;
        } else {
            showErrorMessage(frame, Textual.IMPOSSIVEL_ATUALIZAR);
            return false;
        }
    }

    public boolean remove(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();
        // Se ocorreu tudo bem e foi cadastrado
        if (clientDAO.remove(client)) {
            showInfoMessage(frame, "removido");
            return true;
        } else {
            showErrorMessage(frame, Textual.IMPOSSIVEL_REMOVER);
            return false;
        }
    }

    public Cliente createValidator(String name, String address, String cpf, String phone,
                                   JRadioButton radioButtonFemale, JRadioButton radioButtonMale) {
        char sex = ' ';

        boolean error = Validator.all(address, name, cpf, phone, radioButtonMale, radioButtonFemale);

        if (!error) {
            if (radioButtonFemale.isSelected())
                sex = 'f';
            else if (radioButtonMale.isSelected())
                sex = 'm';

            return new Cliente(cpf, name, phone, address, sex);
        } else {
            return null;
        }
    }

}