package utils;

import java.awt.event.ComponentEvent;
import java.util.function.Predicate;

/**
 * An enumeration of component event types for determining specific component state changes,
 * such as when a component is moved, resized, shown, or hidden. Each enum constant implements
 * a predicate to test for a corresponding event.
 */
public enum WasComponent implements Predicate<ComponentEvent> {
  MOVED((ce) -> ce.getID() == ComponentEvent.COMPONENT_MOVED),
  RESIZED((ce) -> ce.getID() == ComponentEvent.COMPONENT_RESIZED),
  SHOWN((ce) -> ce.getID() == ComponentEvent.COMPONENT_SHOWN),
  HIDDEN((ce) -> ce.getID() == ComponentEvent.COMPONENT_HIDDEN);

  private final Predicate<ComponentEvent> predicate;

  WasComponent(Predicate<ComponentEvent> predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean test(ComponentEvent mouseEvent) {
    return this.predicate.test(mouseEvent);
  }

}