package controller;

import model.Card;
import model.Grid;
import model.Model;
import model.Referee;
import view.View;

import javax.swing.JFrame;
import java.util.List;
import java.util.function.Supplier;

/**
 * To control the flow of the game and handle input and output.
 */
public abstract class ControllerAbstract extends JFrame {

  /**
   * Creates a controller with a model using the following initial conditions.
   *
   * @param gridSupplier    - supplier of a grid
   * @param cardsSupplier   - supplier of a list of cards
   * @param modelSupplier   - supplier of a model
   * @param refereeSupplier - supplier of a referee
   * @param view            - the view
   */
  abstract <OD> void createUsing(Supplier<Grid> gridSupplier,
                                 Supplier<List<Card>> cardsSupplier,
                                 Supplier<Model> modelSupplier,
                                 Supplier<Referee> refereeSupplier,
                                 View<OD> view,
                                 OD output);

}
