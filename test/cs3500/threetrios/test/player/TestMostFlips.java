package cs3500.threetrios.test.player;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.player.MostFlips;
import cs3500.threetrios.player.Move;
import cs3500.threetrios.utils.LineWriter;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

public class TestMostFlips {
  // cases we want to test:
  // 1. empty grid
  // 2. case where one position flips the most cards
  // 3. case where two positions flip the most cards (pick top left)

  // constructing a good test case to meet this criteria
  // empty 2x2
  // place red  1 1 1 1 at 0, 0
  // place blue 2 2 2 2 at 0, 1
  // place red  3 3 3 3 at 1, 0

  // what observations do we care about. we want to take a model a see what a card's attack values
  // and colors at a particular position

  @Test
  public void test() {
    Supplier<Grid> gridSupplier =
            () -> ConfigGrid.scannerToGrid(
                    new Scanner(
                            LineWriter.create()
                                    .line("2 3")
                                    .line("CCX")
                                    .endWith("CCC")
                                    .toString()));
    Supplier<List<Card>> cardSupplier =
            () -> ConfigCard.scannerToCardList(
                    new Scanner(
                            LineWriter.create()
                                    .line("a 1 1 1 1")
                                    .line("b 2 2 2 2")
                                    .line("c 3 3 3 3")
                                    .line("d 4 4 4 4")
                                    .line("e 5 5 5 5")
                                    .endWith("f 5 5 5 5")
                                    .toString()));
    Supplier<Model> modelSupplier = () -> {
      Model m = new ModelBase();
      m.startGame(gridSupplier.get(),
                  cardSupplier.get(),
                  new RefereeDefault());
      return m;
    };

    // move 1
    MostFlips mostFlips = new MostFlips(modelSupplier);
    Optional<Move> move1 = mostFlips.bestMove();
    Supplier<Model> modelSupplier1 = () -> {
      Model m = modelSupplier.get();
      move1.get().accept(m);
      return m;
    };
    Assert.assertTrue(Utils.cardAt(modelSupplier1.get(), 0, 0).isPresent());
    Assert.assertEquals(Utils.cardAt(modelSupplier1.get(), 0, 0).get().getCoachColor(),
                        Coach.Color.Red);
    Assert.assertEquals(Utils.cardAt(modelSupplier1.get(), 0, 0).get().toString(),
                        "a 1 1 1 1");

    // move 2
    mostFlips = new MostFlips(modelSupplier1);
    Optional<Move> move2 = mostFlips.bestMove();
    Supplier<Model> modelSupplier2 = () -> {
      Model m = modelSupplier1.get();
      move2.get().accept(m);
      return m;
    };
    Assert.assertTrue(Utils.cardAt(modelSupplier2.get(), 0, 1).isPresent());
    Assert.assertEquals(Utils.cardAt(modelSupplier2.get(), 0, 0).get().getCoachColor(),
                        Coach.Color.Blue);
    Assert.assertEquals(Utils.cardAt(modelSupplier2.get(), 0, 1).get().getCoachColor(),
                        Coach.Color.Blue);
    Assert.assertEquals(Utils.cardAt(modelSupplier2.get(), 0, 1).get().toString(),
                        "b 2 2 2 2");

    // move 3
    mostFlips = new MostFlips(modelSupplier2);
    Optional<Move> move3 = mostFlips.bestMove();
    Supplier<Model> modelSupplier3 = () -> {
      Model m = modelSupplier2.get();
      move3.get().accept(m);
      return m;
    };
    Assert.assertTrue(Utils.cardAt(modelSupplier3.get(), 1, 1).isPresent());
    Assert.assertEquals(Utils.cardAt(modelSupplier3.get(), 0, 0).get().getCoachColor(),
                        Coach.Color.Red);
    Assert.assertEquals(Utils.cardAt(modelSupplier3.get(), 0, 1).get().getCoachColor(),
                        Coach.Color.Red);
    Assert.assertEquals(Utils.cardAt(modelSupplier3.get(), 1, 1).get().getCoachColor(),
                        Coach.Color.Red);
    Assert.assertEquals(Utils.cardAt(modelSupplier3.get(), 1, 1).get().toString(),
                        "c 3 3 3 3");
  }
}
