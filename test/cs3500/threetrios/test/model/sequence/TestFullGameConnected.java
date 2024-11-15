package cs3500.threetrios.test.model.sequence;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.utils.LineWriter;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.ModelBase;
import org.junit.runners.MethodSorters;

import java.util.Scanner;

import static cs3500.threetrios.controller.TestFiles.GRID_CONNECTED_HOLES;

/**
 * to test a full connected game with two T's.
 */
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
            ConfigGrid.scannerToGrid(GRID_CONNECTED_HOLES),
            ConfigCard.scannerToCardList(new Scanner(
                    LineWriter.create()
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 2 2 2 2")
                            .line("z 1 1 1 1")
                            .line("z 3 3 3 3")
                            .line("z 1 1 1 1")
                            .line("z 2 2 2 2")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 5 5 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 A 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 1 1 1 1")
                            .line("z 6 6 6 6")
                            .line("z 1 1 1 1") // extra
                            .toString()
            )),
            referee);
    Coach red = model.curCoach();
    Assert.assertEquals("The current coach should be Red.",
                        Coach.RED, red);
    // Check that Red has 10 cards
    Assert.assertEquals("Red should have 10 cards in hand.",
                        10, model.curCoachesHands().get(red));
  }

  /**
   * P2Setup: Sets up the game with a few cards placed on the grid.
   * Verifies that the cards are placed correctly.
   */
  @Test
  public void P2Setup() {
    // tests t splits both ways and 1 way in the super domino effect (very cool, can't wait for
    // gui)
    model.placeCard(0, 0, 4);
    model.placeCard(0, 0, 0);
    model.placeCard(0, 1, 4);
    model.placeCard(0, 0, 1);
    model.placeCard(0, 2, 4);
    model.placeCard(0, 0, 2);
    model.placeCard(0, 3, 4);
    model.placeCard(0, 0, 3);
    model.placeCard(0, 4, 4);
    model.placeCard(0, 4, 3);
    model.placeCard(0, 2, 0);  // z 5 5 1 1
    model.placeCard(0, 4, 2);
    model.placeCard(0, 1, 0); // 1 A 1 1
    model.placeCard(0, 4, 0);
    model.placeCard(0, 3, 0);
    model.placeCard(0, 3, 0);
    Assert.assertEquals(Coach.BLUE, cardAt(0, 4).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(1, 4).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(2, 3).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(3, 4).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(4, 4).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(2, 2).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(2, 3).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(1, 0).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(2, 0).getCoach());
    Assert.assertEquals(Coach.BLUE, cardAt(3, 0).getCoach());
  }

  /**
   * to test the domino effect of the T's in the points after branch.
   */
  @Test
  public void P3Dominos() {
    model.placeCard(0, 2, 2);
    Assert.assertEquals(Coach.BLUE, colorAt(1, 0));
    Assert.assertEquals(Coach.BLUE, colorAt(3, 0));
    Assert.assertEquals(Coach.BLUE, colorAt(1, 4));
    Assert.assertEquals(Coach.BLUE, colorAt(3, 4));
  }

  private Card cardAt(int row, int col) {
    return model.curGrid().readOnlyArray2D()[row][col].getCard();
  }

  private Coach colorAt(int row, int col) {
    return model.curGrid().readOnlyArray2D()[row][col].getCard().getCoach();
  }
}