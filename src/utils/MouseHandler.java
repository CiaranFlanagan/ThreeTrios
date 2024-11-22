package utils;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A handler for mouse events that allows custom actions to be performed based on specified
 * conditions. The MouseHandler uses a map of predicates and runnables to determine and execute
 * actions when mouse events occur.
 */
public class MouseHandler extends MouseAdapter {

  protected Map<Predicate<MouseEvent>, Consumer<MouseEvent>> map;

  protected MouseHandler() {
    map = new HashMap<>();
  }

  public static MouseHandler create() {
    return new MouseHandler();
  }

  public MouseHandler handle(Predicate<MouseEvent> question, Consumer<MouseEvent> response) {
    this.map.put(question, response);
    return this;
  }

  /**
   * Registers this handler to listen to all types of mouse events on the specified component.
   *
   * @param c the component to which this handler is attached
   */
  public final void register(Component c) {
    c.addMouseListener(this);
    c.addMouseMotionListener(this);
    c.addMouseWheelListener(this);
  }

  public final void unregister(Component c) {
    c.removeMouseListener(this);
    c.removeMouseMotionListener(this);
    c.removeMouseWheelListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    handleMouseEvent(e);
  }

  private void handleMouseEvent(MouseEvent me) {
    for (Map.Entry<Predicate<MouseEvent>, Consumer<MouseEvent>> entry : map.entrySet()) {
      if (entry.getKey().test(me)) {
        entry.getValue().accept(me);
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    handleMouseEvent(e);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    handleMouseEvent(e);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    handleMouseEvent(e);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    handleMouseEvent(e);
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    handleMouseEvent(e);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    handleMouseEvent(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    handleMouseEvent(e);
  }

}
