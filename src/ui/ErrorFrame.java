package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for displaying error messages in a dialog window.
 * <p>
 * Provides a static method to show an error message dialog with a given title and message.
 * </p>
 *
 * @author Rokas Braidokas
 */
public final class ErrorFrame {
    private ErrorFrame() {}
    /**
     * Shows an error message dialog with the specified title and error message.
     *
     * @param title  the title of the error dialog
     * @param error  the error message to display
     * @param parent the parent component of the dialog, can be {@code null}
     */
    public static void showErrorFrame(String title, String error, Component parent) {
        JOptionPane.showMessageDialog(parent, error, title, JOptionPane.ERROR_MESSAGE);
    }
}
