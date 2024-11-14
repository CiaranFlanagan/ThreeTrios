package cs3500.threetrios.utils.extensions;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MouseHandler extends MouseAdapter {
  protected Map<Predicate<MouseEvent>, Runnable> map;


  
  

  private MouseHandler() {
    map = new HashMap<>();
  }
  
  public static MouseHandler create() {
    return new MouseHandler();
  }
  
  public MouseHandler handle(Predicate<MouseEvent> question, Runnable response) {
    this.map.put(question, response);
    return this;
  }

  private void handleMouseEvent(MouseEvent me) {
    for (Map.Entry<Predicate<MouseEvent>, Runnable> entry : map.entrySet()) {
      if (entry.getKey().test(me)) {
        entry.getValue().run();
      }
    }
  }

  public final void register(Component c) {
    c.addMouseListener(this);
    c.addMouseMotionListener(this);
    c.addMouseWheelListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    handleMouseEvent(e);
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
