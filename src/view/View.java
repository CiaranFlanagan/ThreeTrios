package view;

/**
 * Represents the view for the game of Three Trios.
 */
public interface View<OD> {

  /**
   * To create a render and display at some outputDestination destination.
   *
   * @param outputDestination somewhere that shows the output.
   */
  void renderTo(OD outputDestination);

}
