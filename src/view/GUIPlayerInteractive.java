package view;

import controller.player.Move;
import model.CoachColor;
import model.Model;
import utils.TriConsumer;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class GUIPlayerInteractive extends GUIPlayerBase implements
    BiConsumer<Consumer<Move>, Supplier<Model>> {

  protected TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> hand;
  protected BiConsumer<Move, Consumer<Move>> grid;

  public GUIPlayerInteractive(GUIHandInteractive viewRedHand,
                              GUIHandBase viewBlueHand,
                              GUIGridInteractive viewGrid) {
    super(viewRedHand, viewBlueHand, viewGrid, CoachColor.RED);
    hand = viewRedHand;
    grid = viewGrid;
  }

  public GUIPlayerInteractive(GUIHandBase viewRedHand,
                              GUIHandInteractive viewBlueHand,
                              GUIGridInteractive viewGrid) {
    super(viewRedHand, viewBlueHand, viewGrid, CoachColor.BLUE);
    hand = viewBlueHand;
    grid = viewGrid;
  }

  @Override
  public void accept(Consumer<Move> callback, Supplier<Model> modelSupplier) {
    try {
      Model newModel = modelSupplier.get();
      model = newModel;
    } catch (Exception e) {
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
    update(model);
    Move newMove = Move.create();
    hand.accept(newMove, callback, grid);
  }

}
