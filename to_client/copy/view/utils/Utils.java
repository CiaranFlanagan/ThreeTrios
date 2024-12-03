package copy.view.utils;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

/**
 * A utility class providing helper methods for common GUI-related tasks.
 */
public class Utils {
  /**
   * Displays a pop-up dialog with a message in bold red text.
   * <p>
   * The dialog appears relative to the specified context component and automatically
   * adjusts its size to one-third of the context component's dimensions.
   * </p>
   *
   * @param message the message to display in the dialog
   * @param context the component relative to which the dialog is displayed
   */
  public static void popup(String message, Component context) {
    JDialog dialog = new JDialog();
    dialog.setLayout(new BorderLayout());
    JLabel label = new JLabel("<html>" + message + "</html>");
    label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    label.setForeground(Color.RED);
    dialog.add(label);
    dialog.setSize(context.getWidth() / 3, context.getHeight() / 3);
    dialog.setLocationRelativeTo(context);
    dialog.setAlwaysOnTop(true);
    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    dialog.setVisible(true);
  }
}
