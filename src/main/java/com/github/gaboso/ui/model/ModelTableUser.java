package com.github.gaboso.ui.model;

import com.github.gaboso.constant.Textual;

import javax.swing.table.DefaultTableModel;

public class ModelTableUser extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = {Textual.NAME, Textual.CPF,
            Textual.ADDRESS, Textual.SEX, Textual.PHONE};

    public ModelTableUser(String[][] data) {
        super(data, COLUMN_NAMES);
    }

    public ModelTableUser() {
        super();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}