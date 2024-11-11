package cs3500.threetrios.test.player;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Utils {
  public static void printGrid(Supplier<Grid> gridSupplier) {
    Arrays.stream(gridSupplier.get().readOnly2dCellArray())
            .forEach((row) -> {
              Arrays.stream(row)
                      .forEach((cell) -> {
                        if (cell.canHaveCard())
                          System.out.print("C");
                        else
                          System.out.println("H");
                      });
              System.out.println();
            });
  }

  public static void printCards(Supplier<List<Card>> cardsSupplier) {
    cardsSupplier.get().forEach(System.out::println);
  }

  public static Optional<Card> cardAt(Model model, int row, int col) {
    GridCellReadOnly cell = model.getGrid().readOnly2dCellArray()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard());
    } else {
      return Optional.empty();
    }
  }

}
