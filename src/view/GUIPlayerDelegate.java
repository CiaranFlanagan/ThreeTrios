package view;


import model.CoachColor;
import model.Model;
import model.Move;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GUIPlayerDelegate extends GUIPlayerBase implements
    BiConsumer<Consumer<Move>, Callable<Model>> {

  protected BiConsumer<Consumer<Move>, Callable<Model>> delegate;

  public GUIPlayerDelegate(GUIHandBase viewRedHand,
                           GUIHandBase viewBlueHand,
                           GUIGridBase viewGrid,
                           CoachColor color,
                           BiConsumer<Consumer<Move>, Callable<Model>> delegate) {
    super(viewRedHand, viewBlueHand, viewGrid, color);
    this.delegate = delegate;
  }

  @Override
  public void accept(Consumer<Move> callback, Callable<Model> modelCallable) {
    try {
      model = modelCallable.call();
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.err.println();
      JDialog dialog = new JDialog();
      dialog.setLayout(new BorderLayout());
      JLabel label = new JLabel("<html>" + e.getMessage() + "</html>");
      label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
      label.setForeground(Color.RED);
      dialog.add(label);
      dialog.setSize(this.getWidth() / 3, this.getHeight() / 3);
      dialog.setLocationRelativeTo(this);
      dialog.setAlwaysOnTop(true);
      dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
    }
    updateModel(model);
    delegate.accept(callback, modelCallable);
  }

}
