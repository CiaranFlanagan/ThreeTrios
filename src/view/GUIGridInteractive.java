package view;

import model.Move;
import utils.MouseHandler;
import utils.Utils;
import utils.WasMouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * To represent an interactive grid in the game of Three Trios which can be clicked on.
 */
public class GUIGridInteractive extends GUIGridBase implements
    BiConsumer<Move, Consumer<Move>> {

  private Move move;
  private Consumer<Move> callback;


  /**
   * Constructor.
   *
   * @param gridView the artist of the grid
   */
  public GUIGridInteractive(DrawGrid gridView) {
    super(gridView);
    new OnMouse().init();
    this.setVisible(true);
  }

  @Override
  public void accept(Move move, Consumer<Move> callback) {
    this.move = move;
    this.callback = callback;
  }

  /**
   * The mouse handler of this grid to handle clicks.
   */
  private class OnMouse extends MouseHandler {

    private void init() {
      this.handle(WasMouse.CLICKED, this::handleClick)
          .register(GUIGridInteractive.this);
    }

    private void handleClick(MouseEvent me) {
      if (curGrid.isFull()) {
        this.unregister(GUIGridInteractive.this);
      }
      if (move == null) {
        Utils.popup("no card selected", GUIGridInteractive.this);
        return;
      }
      Point cell = view.cellAt(me.getPoint(), curGrid, curImage);
      // update the move
      move.row = cell.y;
      move.col = cell.x;

      // call player
      callback.accept(move);

      // reset callback to make sure that you can't spam when it's not your turn
      move = null;
      callback = (m -> {
      });

      GUIGridInteractive.this.repaint();
    }

  }

}
