package com.github.gaboso.helper;

import com.github.gaboso.constant.Textual;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public abstract class ScreenHelper {

    protected static final Color GREY = new Color(192, 192, 192);
    protected static final Color RED = new Color(244, 67, 54);
    protected static final Color GREEN = new Color(67, 160, 71);

    private static final LineBorder LINE_BORDER = new LineBorder(GREY, 1, true);

    protected static TitledBorder makeBorder(String title) {
        return new TitledBorder(LINE_BORDER, title, TitledBorder.LEADING, TitledBorder.TOP, null, GREY);
    }

    protected void showErrorMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(
            frame,
            message,
            Textual.ERROR,
            JOptionPane.ERROR_MESSAGE,
            getImageIcon(Textual.NOT_OK_48)
        );
    }

    protected static ImageIcon getImageIcon(String imageName) {
        return new ImageIcon(ScreenHelper.class.getResource("/img/" + imageName + ".png"));
    }

    protected void showInfoMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(
            frame,
            message,
            capitalize(message),
            JOptionPane.INFORMATION_MESSAGE,
            getImageIcon(Textual.INFO_48)
        );
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase().concat(word.substring(1));
    }

}