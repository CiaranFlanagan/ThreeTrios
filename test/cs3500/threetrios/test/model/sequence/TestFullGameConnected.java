package cs3500.threetrios.test.model.sequence;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.utils.LineWriter;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.ModelBase;
import org.junit.runners.MethodSorters;

import java.util.Scanner;

import static cs3500.threetrios.controller.TestFiles.CC_LARGE;
import static cs3500.threetrios.controller.TestFiles.GRID_CONNECTED_HOLES;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFullGameConnected {
  private static final ModelBase model = new ModelBase();
  private static final Referee referee = new RefereeDefault();
  private static GridCellReadOnly[][] state;

  /**
   * P1Start: Starts the game with the large grid and large card set.
   * Verifies that the current coach is Red and both coaches have 10 cards each.
   */
  @Test
  public void P1Start() {
    model.startGame(
            ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_CONNECTED_HOLES)),
            ConfigCard.scannerToCardList(new Scanner(
                    LineWriter.create()
                            .line("z 9 9 9 9")
                            .line("z 8 8 8 8")
                            .line("z 7 7 7 7")
                            .line("z 6 6 6 6")
                            .line("z 5 5 5 5")
                            .line("z 4 4 4 4")
                            .line("z 3 3 3 3")
                            .line("z 2 2 2 2")
                            .line("z 1 1 1 1")
                            .line("z 9 9 9 9")
                            .line("z 8 8 8 8")
                            .line("z 7 7 7 7")
                            .line("z 6 6 6 6")
                            .line("z A A A A")
                            .line("z A A A A")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .toString()
            )),
            referee);
    Coach red = model.getCurrentCoach();
    Assert.assertEquals("The current coach should be Red.",
            Coach.Color.Red, red.getColor());
    // Check that Red has 10 cards
    Assert.assertEquals("Red should have 10 cards in hand.",
            10, red.getHand().size());
  }

  @Test
  public void P2Setup() {
    // first domino should look like
    // R B R B R
    //   _ _ _ B
    //     R B R
    model.placeCard(0, 0, 0); // c1 - red
    model.placeCard(0, 0, 1);
    model.placeCard(0, 0, 2);
    model.placeCard(0, 0, 3);
    model.placeCard(0, 0, 4);
    model.placeCard(0, 1, 4);
    model.placeCard(0, 2, 4);
    model.placeCard(0, 2, 3);
    model.placeCard(0, 2, 2);
    //[][] ...
    //[]_ _ _
    //[]R B R B
    //make the bottom look like this
    model.placeCard(0, 4, 4); // c1 - red
    model.placeCard(0, 4, 3);
    model.placeCard(0, 4, 2);
    model.placeCard(0, 4, 1);
    // test assumptions of setup

    Assert.assertEquals(Coach.Color.Red, colorAt(0, 0));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 1));
    Assert.assertEquals(Coach.Color.Red, colorAt(0, 2));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 3));
    Assert.assertEquals(Coach.Color.Red, colorAt(0, 4));
    Assert.assertEquals(Coach.Color.Blue, colorAt(1, 4));
    Assert.assertEquals(Coach.Color.Red, colorAt(2, 4));
    Assert.assertEquals(Coach.Color.Blue, colorAt(2, 3));
    Assert.assertEquals(Coach.Color.Red, colorAt(2, 2));
    Assert.assertEquals(Coach.Color.Blue, colorAt(4, 4));
    Assert.assertEquals(Coach.Color.Red, colorAt(4, 3));
    Assert.assertEquals(Coach.Color.Blue, colorAt(4, 2));
    Assert.assertEquals(Coach.Color.Red, colorAt(4, 1));
  }

  @Test
  public void P3Dominos() {
    model.placeCard(0, 1, 0);
    // R B R B R
    // ! _ _ _ B    ! -> dominoeffect and everyone is blue
    // [][]R B R
    model.placeCard(0, 3, 4);
    // B B B B B
    // B _ _ _ B
    // [][]B B B
    // []_ _ _ !   -> domino effect to create
    // []R B R B

    // B B B B B
    // B _ _ _ B
    // [][]R R R
    // []_ _ _ R   -> domino effect to create
    // []R R R R
    Assert.assertEquals(Coach.Color.Blue, colorAt(1, 0));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 0));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 1));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 2));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 3));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 4));
    Assert.assertEquals(Coach.Color.Blue, colorAt(0, 5));

    Assert.assertEquals(Coach.Color.Red, colorAt(2, 2));
    Assert.assertEquals(Coach.Color.Red, colorAt(2, 3));
    Assert.assertEquals(Coach.Color.Red, colorAt(2, 4));
    Assert.assertEquals(Coach.Color.Red, colorAt(3, 4));
    Assert.assertEquals(Coach.Color.Red, colorAt(4, 4));
    Assert.assertEquals(Coach.Color.Red, colorAt(4, 3));
    Assert.assertEquals(Coach.Color.Red, colorAt(4, 2));
    Assert.assertEquals(Coach.Color.Red, colorAt(4, 1));
  }

  private Coach.Color colorAt(int row, int col) {
    return model.getGrid().readOnly2dCellArray()[row][col].getCard().getCoach().getColor();
  }

}
