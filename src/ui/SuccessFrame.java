package ui;

import javax.swing.*;
import java.awt.*;

public class SuccessFrame {
    public static void showSuccessFrame(String title, String success, Component parent) {
        JOptionPane.showMessageDialog(parent, success, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
