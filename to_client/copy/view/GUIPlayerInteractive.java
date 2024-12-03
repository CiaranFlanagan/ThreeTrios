package copy.view;

import copy.model.CoachColor;
import copy.model.GamePlayer;
import copy.model.Model;
import copy.model.ModelForView;
import copy.model.Move;
import copy.utils.MouseHandler;
import copy.utils.TriConsumer;
import copy.utils.Utils;
import copy.utils.WasMouse;

import javax.swing.JPanel;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * To represent a player in the game of three trios as a gui and also handle interactions
 * accordingly.
 */
public class GUIPlayerInteractive extends GUIPlayerDelegate implements GamePlayer {

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
    super(redHand, blueHand, grid, CoachColor.RED);
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
    super(redHand, blueHand, grid, CoachColor.BLUE);
    hand = blueHand;
    this.grid = grid;
    setGlassPane(new GlassPane());
  }

  @Override
  public void accept(Consumer<Move> callback, Supplier<Model> modelSupplier) {
    if (modelSupplier.get() == null) {
      return;
    }
    renderModel(new ModelForView(modelSupplier.get()));
    configGlassPlane();
    Move newMove = Move.create();
    hand.accept(newMove, callback, grid);
    configGlassPlane();
  }

  protected void configGlassPlane() {
    getGlassPane().setVisible(model.curCoach() != this.coachColor);
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
