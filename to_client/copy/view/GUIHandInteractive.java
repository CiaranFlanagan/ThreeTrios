package copy.view;


import copy.model.CoachColor;
import copy.model.Move;
import copy.view.utils.MouseHandler;
import copy.view.utils.TriConsumer;
import copy.view.utils.WasMouse;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * To represent an interactive representation of a hand in Three Trios that can handle
 * clicks and hovers to select a card in the hand.
 */
public class GUIHandInteractive extends GUIHandBase implements
    TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> {

  // clicks
  protected Optional<Integer> clickPos;
  protected Optional<Integer> hoverPos;
  // association
  CoachColor coachColor;
  // flow
  private Move move;
  private Consumer<Move> callback;
  private BiConsumer<Move, Consumer<Move>> forwardCallBack;

  /**
   * Constructor.
   * @param handView the artist of the hand
   */
  public GUIHandInteractive(DrawHand handView) {
    super(handView);
    this.clickPos = Optional.empty();
    this.hoverPos = Optional.empty();
    new OnMouse().register(this);
  }

  @Override
  public void updateHand(List<Card> hand) {
    if (!hand.isEmpty()) {
      this.coachColor = hand.get(0).getCoach();
      this.clickPos = Optional.empty();
    }
    super.updateHand(hand);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!hand.isEmpty()) {
      renderHover(g);
      renderSelected(g);
    }
  }

  private void renderHover(Graphics g) {
    g.setColor(VISIBLE_HOVER);
    renderCellFeedback(g, hoverPos);
  }

  private void renderSelected(Graphics g) {
    g.setColor(VISIBLE_SELECTED);
    renderCellFeedback(g, clickPos);
  }

  private void renderCellFeedback(Graphics g, Optional<Integer> pos) {
    if (pos.isPresent()) {
      Rectangle r = view.idxToBoundingBox(pos.get(), hand, currentImage);
      g.drawRect(r.x, r.y, r.width, r.height);
    }
  }

  @Override
  public void accept(Move move,
                     Consumer<Move> callback,
                     BiConsumer<Move, Consumer<Move>> forwardCallBack) {
    this.move = move;
    this.callback = callback;
    this.forwardCallBack = forwardCallBack;
  }

  private class OnMouse extends MouseHandler {

    private OnMouse() {
      this.handle(WasMouse.CLICKED, this :: handleClick)
          .handle(WasMouse.MOVED, this :: handleHover)
          .handle(m -> hand.isEmpty(), m -> this.unregister(GUIHandInteractive.this));
    }

    private void handleClick(MouseEvent me) {
      int selectedIdx = view.idxOfHandAt(me.getPoint(), hand, currentImage);
      if (clickPos.isPresent() && clickPos.get() == selectedIdx) {
        clickPos = Optional.empty();
        move = null;
        forwardCallBack.accept(null, callback);
      } else {
        clickPos = Optional.of(selectedIdx);

        // update move
        move = Move.create();
        move.handIdx = selectedIdx;

        // forward callback
        forwardCallBack.accept(move, callback);

      }

      GUIHandInteractive.this.repaint();

    }

    private void handleHover(MouseEvent me) {
      int selectedIdx = view.idxOfHandAt(me.getPoint(), hand, currentImage);
      hoverPos = Optional.of(selectedIdx);
      GUIHandInteractive.this.repaint();
    }

  }

}
