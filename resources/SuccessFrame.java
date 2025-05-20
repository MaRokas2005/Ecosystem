import javax.swing.*;
import java.awt.*;

/**
 * Utility class for displaying success message dialogs.
 * Provides a simple static method to show informational pop-up windows
 * to notify the user of successful operations.
 *
 * @author Rokas Braidokas
 */
public final class SuccessFrame {
    private SuccessFrame() {}
    /**
     * Displays a success message dialog with the specified title and message,
     * centered relative to the given parent component.
     *
     * @param title the title of the dialog window
     * @param success the success message to display
     * @param parent the parent component relative to which the dialog is displayed; can be null
     */
    public static void showSuccessFrame(String title, String success, Component parent) {
        JOptionPane.showMessageDialog(parent, success, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
