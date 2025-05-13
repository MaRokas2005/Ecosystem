package ui;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame {
    public static void showErrorFrame(String title, String error, Component parent) {
        JOptionPane.showMessageDialog(parent, error, title, JOptionPane.ERROR_MESSAGE);
    }
}
