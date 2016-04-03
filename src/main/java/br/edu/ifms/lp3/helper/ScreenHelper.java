package br.edu.ifms.lp3.helper;

import br.edu.ifms.lp3.constant.Textual;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Classe que contém itens de layout que são comuns na tela
 */
public abstract class ScreenHelper {

    // Cores
    private static final Color GREY = new Color(192, 192, 192);
    protected static final Color BLACK = new Color(51, 51, 51);
    public static final Color RED = new Color(244, 67, 54);
    public static final Color GREEN = new Color(67, 160, 71);

    private static LineBorder lineBorder = new LineBorder(GREY, 1, true);

    /**
     * Método para criar uma borda
     *
     * @param title - Título que será exibido na borda
     * @return retorna uma borda
     */
    protected static TitledBorder makeBorder(String title) {
        return new TitledBorder(lineBorder, title, TitledBorder.LEADING, TitledBorder.TOP, null, BLACK);
    }

    /**
     * Método para pegar uma imagem
     *
     * @param imageName - Nome da imagem
     * @return retorna uma imagem
     */
    protected static ImageIcon getImageIcon(String imageName) {
        return new ImageIcon(ScreenHelper.class.getResource("/img/" + imageName + ".png"));
    }

    /**
     * Método pára exibir mensagem de erro
     *
     * @param frame   - JFrame a qual a menagem faz parte
     * @param message - Mensagem a ser exibida
     */
    protected void showMessageError(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, Textual.ERRO, JOptionPane.ERROR_MESSAGE,
                getImageIcon(Textual.INCORRETO_48));
    }
}
