package view;

import model.Move;
import model.Card;
import model.CoachColor;
import utils.MouseHandler;
import utils.TriConsumer;
import utils.WasMouse;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GUIHandInteractive extends GUIHandBase implements
    TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> {

  // association
  CoachColor coachColor;

  // clicks
  protected Optional<Integer> clickPos;
  protected Optional<Integer> hoverPos;

  // flow
  private Move move;
  private Consumer<Move> callback;
  private BiConsumer<Move, Consumer<Move>> forwardCallBack;

  public GUIHandInteractive(DrawHand view) {
    super(view);
    this.clickPos = Optional.empty();
    this.hoverPos = Optional.empty();
    new OnMouse().register(this);
  }

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

  private void renderSelected(Graphics g) {
    g.setColor(VISIBLE_SELECTED);
    renderCellFeedback(g, clickPos);
  }


  private void renderHover(Graphics g) {
    g.setColor(VISIBLE_HOVER);
    renderCellFeedback(g, hoverPos);
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
      } else {
        clickPos = Optional.of(selectedIdx);

        // TODO

        // update move
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
