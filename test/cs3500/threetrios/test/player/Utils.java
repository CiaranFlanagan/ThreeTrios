package cs3500.threetrios.test.player;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Utility class for testing purposes in the Three Trios game. Provides helper methods for
 * printing the grid structure, displaying a list of cards, and retrieving a card at a specific
 * grid position.
 */
public class Utils {

  /**
   * Prints the grid layout to the console. Each cell is represented by "C" for a cell that
   * can have a card and "H" for a hole that cannot contain a card.
   *
   * @param gridSupplier a Supplier that provides the Grid to print
   */
  public static void printGrid(Supplier<Grid> gridSupplier) {

    Arrays.stream(gridSupplier.get().readOnlyArray2D())
          .forEach((row) -> {
            Arrays.stream(row)
                  .forEach((cell) -> {
                    if (cell.canHaveCard()) {
                      System.out.print("C");
                    } else {
                      System.out.print("C");
                    }
                  });
            System.out.println();
          });
  }

  /**
   * Prints each card in the supplied list to the console.
   *
   * @param cardsSupplier a Supplier that provides the list of cards to print
   */
  public static void printCards(Supplier<List<Card>> cardsSupplier) {
    cardsSupplier.get().forEach(System.out::println);
  }

  /**
   * Retrieves the card at a specific position in the grid if a card is present.
   *
   * @param model the Model containing the grid
   * @param row the row index of the desired position
   * @param col the column index of the desired position
   * @return an Optional containing the Card at the specified position, or empty if no card exists
   */
  public static Optional<Card> cardAt(Model model, int row, int col) {
    GridCellReadOnly cell = model.curGrid().readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard());
    } else {
      return Optional.empty();
    }
  }

}
