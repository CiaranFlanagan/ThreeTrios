package cs3500.threetrios.view;

/**
 * Represents the view for the game of Three Trios.
 */
public interface View<OD> {
  /**
   * To create a render and display at some outputDestination destination.
   * @param outputDestination - Somewhere that shows
   */
  void renderTo(OD outputDestination);
}
