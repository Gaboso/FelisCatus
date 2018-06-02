package com.github.gaboso.ui.model;

import java.util.List;

public class Matrix {

    private Matrix() {
    }

    public static String[][] create(List<String[]> dataFromDB) {
        int size = dataFromDB.size();

        String[][] table = new String[size][5];

        for (int i = 0; i < size; i++) {
            Object[] line = dataFromDB.get(i);

            for (int j = 0; j < 5; j++) {
                table[i][j] = line[j].toString();
            }
        }

        return table;
    }

}