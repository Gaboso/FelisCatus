package com.github.gaboso.ui.model;

import java.util.List;

public class Matrix {

    public static final int QUANTITY_OF_COLUMNS = 5;

    private Matrix() {
    }

    public static String[][] parse(List<String[]> dataFromDB) {
        int quantityOfLines = dataFromDB.size();

        String[][] table = new String[quantityOfLines][QUANTITY_OF_COLUMNS];

        for (int currentLine = 0; currentLine < quantityOfLines; currentLine++) {
            Object[] lineData = dataFromDB.get(currentLine);

            for (int currentColumn = 0; currentColumn < QUANTITY_OF_COLUMNS; currentColumn++) {
                table[currentLine][currentColumn] = lineData[currentColumn].toString();
            }
        }

        return table;
    }

}