package cs3500.threetrios.utils.extensions;

import java.awt.event.MouseEvent;
import java.util.function.Predicate;

public enum WasMouse implements Predicate<MouseEvent> {
  CLICKED ((me) -> me.getID() == MouseEvent.MOUSE_CLICKED),
  PRESSED ((me) -> me.getID() == MouseEvent.MOUSE_PRESSED),
  RELEASED ((me) -> me.getID() == MouseEvent.MOUSE_RELEASED),
  MOVED ((me) -> me.getID() == MouseEvent.MOUSE_MOVED),
  ENTERED ((me) -> me.getID() == MouseEvent.MOUSE_ENTERED),
  EXITED ((me) -> me.getID() == MouseEvent.MOUSE_EXITED),
  DRAGGED ((me) -> me.getID() == MouseEvent.MOUSE_DRAGGED),
  SCROLLED ((me) -> me.getID() == MouseEvent.MOUSE_WHEEL);

  private final Predicate<MouseEvent> predicate;

  WasMouse(Predicate<MouseEvent> predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean test(MouseEvent mouseEvent) {
    return this.predicate.test(mouseEvent);
  }

}