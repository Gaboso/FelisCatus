package com.github.gaboso.ui.model;

import com.github.gaboso.constant.Textual;

import javax.swing.table.DefaultTableModel;

public class ModelTableClient extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAME = {Textual.NOME, Textual.CPF,
            Textual.ENDERECO, Textual.SEXO, Textual.TELEFONE};

    public ModelTableClient(String[][] data) {
        super(data, COLUMN_NAME);
    }

    public ModelTableClient() {
        super();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}