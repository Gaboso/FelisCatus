package com.github.gaboso.ui.model;

import java.util.List;

/**
 * Classe que vai modelar os dados do banco e colocar em uma Matriz
 */
public class Matriz {

    public String[][] mountMatriz(List<String[]> dataFromDB) {
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