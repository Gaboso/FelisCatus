package br.edu.ifms.lp3.ui.model;

import br.edu.ifms.lp3.constant.Textual;

import javax.swing.table.DefaultTableModel;

/**
 * Modelo de tabela para utilização de tabela no Swing
 */
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