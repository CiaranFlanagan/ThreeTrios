package view;

import controller.player.Move;
import utils.MouseHandler;
import utils.WasMouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GUIGridInteractive extends GUIGridBase implements
    BiConsumer<Move, Consumer<Move>> {

  private Move move;
  private Consumer<Move> callback;

  public GUIGridInteractive(DrawGrid view) {
    super(view);
    new OnMouse().init();
    this.setVisible(true);
  }

  @Override
  public void accept(Move move, Consumer<Move> callback) {
    this.move = move;
    this.callback = callback;
  }

  private class OnMouse extends MouseHandler {

    public void init() {
      this.handle(WasMouse.CLICKED, this :: handleClick)
          .register(GUIGridInteractive.this);
    }

    private void handleClick(MouseEvent me) {
      if (curGrid.isFull()) {
        this.unregister(GUIGridInteractive.this);
      } Point cell = view.cellAt(me.getPoint(), curGrid, curImage);
      // update the move
      move.row = cell.y;
      move.col = cell.x;

      // call player
      callback.accept(move);

      // reset callback to make sure that you can't spam when it's not your turn
      callback = (m -> {});

      GUIGridInteractive.this.repaint();
    }

  }

}
