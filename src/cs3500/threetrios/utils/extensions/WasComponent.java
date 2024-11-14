package cs3500.threetrios.utils.extensions;

import java.awt.event.ComponentEvent;
import java.util.function.Predicate;

public enum WasComponent implements Predicate<ComponentEvent> {
  MOVED ((ce) -> ce.getID() == ComponentEvent.COMPONENT_MOVED),
  RESIZED ((ce) -> ce.getID() == ComponentEvent.COMPONENT_RESIZED),
  SHOWN ((ce) -> ce.getID() == ComponentEvent.COMPONENT_SHOWN),
  HIDDEN ((ce) -> ce.getID() == ComponentEvent.COMPONENT_HIDDEN);

  private final Predicate<ComponentEvent> predicate;

  WasComponent(Predicate<ComponentEvent> predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean test(ComponentEvent mouseEvent) {
    return this.predicate.test(mouseEvent);
  }

}