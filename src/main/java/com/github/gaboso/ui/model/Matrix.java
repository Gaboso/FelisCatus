package com.github.gaboso.ui.model;

import java.util.List;

public class Matrix {

    private Matrix() {
    }

    public static String[][] create(List<String[]> dataFromDB) {
        int size = dataFromDB.size();

        String[][] table = new String[size][5];

        for (int line = 0; line < size; line++) {
            Object[] lineData = dataFromDB.get(line);

            for (int column = 0; column < 5; column++) {
                table[line][column] = lineData[column].toString();
            }
        }

        return table;
    }

}