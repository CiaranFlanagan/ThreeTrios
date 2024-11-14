package cs3500.threetrios.utils.extensions;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ComponentHandler implements ComponentListener {
  protected Map<Predicate<ComponentEvent>, Runnable> map;

  public ComponentHandler() {
    map = new HashMap<>();
  }
  
  public ComponentHandler handle(Predicate<ComponentEvent> question, Runnable response) {
    this.map.put(question, response);
    return this;
  }

  private void handleComponentEvent(ComponentEvent me) {
    for (Map.Entry<Predicate<ComponentEvent>, Runnable> entry : map.entrySet()) {
      if (entry.getKey().test(me)) {
        entry.getValue().run();
      }
    }
  }

  public final void register(Component c) {
    c.addComponentListener(this);
  }

  @Override
  public void componentResized(ComponentEvent e) {
    handleComponentEvent(e);
  }

  @Override
  public void componentMoved(ComponentEvent e) {
    handleComponentEvent(e);
  }

  @Override
  public void componentShown(ComponentEvent e) {
    handleComponentEvent(e);
  }

  @Override
  public void componentHidden(ComponentEvent e) {
    handleComponentEvent(e);
  }

}
