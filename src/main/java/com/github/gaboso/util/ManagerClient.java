package com.github.gaboso.util;

import com.github.gaboso.constant.Textual;
import com.github.gaboso.dao.UserDAO;
import com.github.gaboso.helper.ScreenHelper;
import com.github.gaboso.model.User;

import javax.swing.*;

public class ManagerClient extends ScreenHelper {

    public boolean save(User user, JFrame frame) {
        UserDAO userDAO = new UserDAO();
        boolean isSaved = userDAO.save(user) != null;

        return base(frame, isSaved, "efetuado", Textual.CPF_JA_CADASTRADO);
    }

    public boolean update(User user, JFrame frame) {
        UserDAO userDAO = new UserDAO();
        boolean isUpdated = userDAO.update(user) != null;

        return base(frame, isUpdated, "atualizado", Textual.COULD_T_UPDATE_USER);
    }

    public boolean remove(User user, JFrame frame) {
        UserDAO userDAO = new UserDAO();
        boolean isRemoved = userDAO.remove(user);

        return base(frame, isRemoved, "removido", Textual.COULD_T_DELETE_USER);
    }

    private boolean base(JFrame frame, boolean result, String sucessMessage, String errorMessage) {
        if (result) {
            showInfoMessage(frame, sucessMessage);
            return true;
        } else {
            showErrorMessage(frame, errorMessage);
            return false;
        }
    }

    public User newValidUser(String name, String address, String cpf, String phone,
                             JRadioButton radioButtonFemale, JRadioButton radioButtonMale) {
        char sex = ' ';

        boolean error = Validator.all(address, name, cpf, phone, radioButtonMale, radioButtonFemale);

        if (!error) {
            if (radioButtonFemale.isSelected()) {
                sex = 'f';
            } else if (radioButtonMale.isSelected()) {
                sex = 'm';
            }

            return new User(cpf, name, phone, address, sex);
        }

        return null;
    }

}