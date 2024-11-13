package cs3500.threetrios.view;

/**
 * Represents the view for the game of Three Trios.
 */
public interface View<OD> {
  /**
   * to render to some outputDestination destination
   * @param outputDestination - Somewhere that shows
   */
  void render(OD outputDestination);
}
