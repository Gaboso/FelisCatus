package br.edu.ifms.lp3.ui.screen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Classe que contém itens de layout que são comuns na tela
 */
public abstract class ScreenHelper {

    public static Color grey = new Color(192, 192, 192);
    public static Color black = new Color(51, 51, 51);
    public static Color red = new Color(244, 67, 54);
    public static Color green = new Color(67, 160, 71);

    private static LineBorder lineBorder = new LineBorder(grey, 1, true);

    /**
     * Método para criar uma borda
     *
     * @param title - Título que será exibido na borda
     * @return retorna uma borda
     */
    public static TitledBorder makeBorder(String title) {
        return new TitledBorder(lineBorder, title, TitledBorder.LEADING, TitledBorder.TOP, null, black);
    }

    /**
     * Método para pegar uma imagem
     *
     * @param imageName - Nome da imagem
     * @return retorna uma imagem
     */
    public static Icon getIcon(String imageName) {
        return new ImageIcon(ScreenHelper.class.getResource("/img/" + imageName + ".png"));
    }
}
