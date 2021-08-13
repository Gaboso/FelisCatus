package com.github.gaboso.util;

import com.github.gaboso.constant.Textual;
import com.github.gaboso.dao.UserDAO;
import com.github.gaboso.helper.ScreenHelper;
import com.github.gaboso.model.User;

import javax.swing.*;

public class ManagerClient extends ScreenHelper {

    private final UserDAO userDAO;

    public ManagerClient() {
        userDAO = new UserDAO();
    }

    public boolean save(User user, JFrame frame) {
        boolean isSaved = userDAO.save(user) != null;

        return base(frame, isSaved, "Registration successfully complete", Textual.CPF_ALREADY_EXISTS);
    }

    private boolean base(JFrame frame, boolean isOk, String sucessMessage, String errorMessage) {
        if (isOk) {
            showInfoMessage(frame, sucessMessage);
            return true;
        } else {
            showErrorMessage(frame, errorMessage);
            return false;
        }
    }

    public boolean update(User user, JFrame frame) {
        boolean isUpdated = userDAO.update(user) != null;

        return base(frame, isUpdated, "Registration updated successfully", Textual.UPDATE_USER_ERROR);
    }

    public boolean remove(User user, JFrame frame) {
        boolean isRemoved = userDAO.remove(user);

        return base(frame, isRemoved, "Registration removed successfully", Textual.DELETE_USER_ERROR);
    }

    public User newValidUser(String name, String address, String cpf, String phone,
        boolean isFemale, boolean isMale) {

        boolean error = Validator.all(address, name, cpf, phone, isMale, isFemale);

        if (error) {
            return null;
        }

        char sex;
        if (isFemale) {
            sex = 'f';
        } else if (isMale) {
            sex = 'm';
        } else {
            sex = ' ';
        }

        return new User(cpf, name, phone, address, sex);
    }

}