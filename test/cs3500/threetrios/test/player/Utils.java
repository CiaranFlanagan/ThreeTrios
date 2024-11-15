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

  public static void printCards(Supplier<List<Card>> cardsSupplier) {
    cardsSupplier.get().forEach(System.out::println);
  }

  public static Optional<Card> cardAt(Model model, int row, int col) {
    GridCellReadOnly cell = model.curGrid().readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard());
    } else {
      return Optional.empty();
    }
  }

}
