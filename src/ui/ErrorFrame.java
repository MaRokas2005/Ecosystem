package ui;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame {
    public static void showErrorFrame(String title, String error, Component parent) {
        JFrame errorFrame = new JFrame(title);
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setSize(600, 200);
        errorFrame.setLocationRelativeTo(parent);
        JTextArea errorField = new JTextArea(error, 600, 200);
        errorField.setFont(new Font("Serif", Font.PLAIN, 20));
        errorField.setMargin(new Insets(50, 100, 0, 0));
        errorField.setLineWrap(true);
        errorField.setEditable(false);
        errorField.setCaretColor(errorFrame.getBackground());
        errorFrame.add(errorField);
        errorFrame.setVisible(true);
    }
}
