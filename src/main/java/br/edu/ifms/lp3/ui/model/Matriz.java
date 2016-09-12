package br.edu.ifms.lp3.ui.model;

import java.util.List;

/**
 * Classe que vai modelar os dados do banco e colocar em uma Matriz
 */
public class Matriz {

    /**
     * Método para montar matriz com dados do BD
     *
     * @param dataFromDB - Dados pegos no BD
     * @return Retorna matriz com os dados dos clientes
     */
    public String[][] mountMatriz(List<String[]> dataFromDB) {
        // Tamanho do vetor pego no banco
        int size = dataFromDB.size();
        // Matriz que vai ser preenchida com dados do banco com tamanho da linha de 5 colunas
        String[][] table = new String[size][5];

        for (int i = 0; i < size; i++) {
            Object[] line = dataFromDB.get(i);

            // Guarda partes da linha na matriz
            for (int j = 0; j < 5; j++)
                table[i][j] = line[j].toString();
        }

        return table;
    }

}