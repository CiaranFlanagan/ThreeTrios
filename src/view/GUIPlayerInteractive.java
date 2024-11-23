package view;

import model.CoachColor;
import model.Model;
import model.Move;
import utils.MouseHandler;
import utils.TriConsumer;
import utils.Utils;
import utils.WasMouse;

import javax.swing.JPanel;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * To represent a player in the game of three trios as a gui and also handle interactions
 * accordingly.
 */
public class GUIPlayerInteractive extends GUIPlayerDelegate implements
    BiConsumer<Consumer<Move>, Callable<Model>> {

  protected TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> hand;
  protected BiConsumer<Move, Consumer<Move>> grid;

  /**
   * Constructor.
   * @param redHand  an interactive red hand
   * @param blueHand a base blue hand
   * @param grid     an interactive grid
   */
  public GUIPlayerInteractive(GUIHandInteractive redHand,
                              GUIHandBase blueHand,
                              GUIGridInteractive grid) {
    super(redHand, blueHand, grid, CoachColor.RED, null);
    hand = redHand;
    this.grid = grid;
    setGlassPane(new GlassPane());
  }

  /**
   * Constructor.
   * @param redHand  a base red hand
   * @param blueHand an interactive blue hand
   * @param grid     an interactive grid
   */
  public GUIPlayerInteractive(GUIHandBase redHand,
                              GUIHandInteractive blueHand,
                              GUIGridInteractive grid) {
    super(redHand, blueHand, grid, CoachColor.BLUE, null);
    hand = blueHand;
    this.grid = grid;
    setGlassPane(new GlassPane());
  }

  @Override
  public void accept(Consumer<Move> callback, Callable<Model> modelCallable) {
    if (!propagateCallable(modelCallable)) {
      configGlassPlane();
      Move newMove = Move.create();
      hand.accept(newMove, callback, grid);
      configGlassPlane();
    }
  }

  protected void configGlassPlane() {
    if (model.curCoach() == this.coach) {
      getGlassPane().setVisible(false);
    } else {
      getGlassPane().setVisible(true);
    }
  }

  protected static class GlassPane extends JPanel {

    protected GlassPane() {
      setOpaque(false);
      MouseHandler.create()
                  .handle(WasMouse.CLICKED, this :: handleClick)
          .handle(WasMouse.MOVED, InputEvent :: consume)
                  .register(this);
      this.setVisible(true);
    }

    private void handleClick(MouseEvent me) {
      Utils.popup("not your turn", this);
      me.consume();
    }

  }

}
