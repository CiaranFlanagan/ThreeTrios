package view;

import java.awt.Graphics;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.util.function.Consumer;
import model.ModelReadOnly;
import model.Move;
import utils.MouseHandler;

/**
 * Grid that shows hints.
 */
public class GuiGridShowHints extends GUIGridInteractive {
  protected boolean showHints;
  protected DrawGridShowHints hintGrid;

  protected ModelReadOnly model;

  protected int idx;

  /**
   * Constructor.
   *
   * @param hintGrid the artist of the grid
   */
  public GuiGridShowHints(DrawGridShowHints hintGrid) {
    super(hintGrid);
    this.hintGrid = hintGrid;
    JPopupMenu menu = new JPopupMenu("toggles");
    JMenuItem item = new JMenuItem("toggle hints");
    menu.add(item);
    item.addActionListener(l -> {
      showHints = !showHints;
      repaint();
    });
    this.addMouseListener(MouseHandler.create().handle(me -> me.isPopupTrigger(),
        me -> menu.show(this, me.getX(), me.getY())));
  }

  @Override
  public void paint(Graphics g) {
    if (model == null) {
      return;
    }
    if (showHints) {
      hintGrid.toggleHints(true);
      hintGrid.setNumFlippedIfPlaced((r, c) -> {
        if (model.canPlayAt(r, c)) {
          return model.numFlippedIfPlaced(
              model.curCoachesHands()
                   .get(model.curCoach()).get(idx), r, c);
        } else {
          return 0;
        }
      });
    } else {
      hintGrid.toggleHints(false);
    }
    super.paint(g);
  }

  public void setModel(ModelReadOnly model) {
    this.model = model;
  }

  @Override
  public void accept(Move move, Consumer<Move> callback) {
    idx = move == null ? -1 : move.handIdx;
    super.accept(move, m -> {
      showHints = false;
      this.repaint();
      callback.accept(move);
    });
  }
}
