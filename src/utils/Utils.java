package utils;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class Utils {
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
