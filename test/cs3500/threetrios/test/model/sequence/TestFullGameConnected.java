package cs3500.threetrios.test.model.sequence;

import cs3500.threetrios.controller.TestFiles;
import org.junit.Assert;
import org.junit.Test;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.ModelBase;

import static cs3500.threetrios.controller.TestFiles.CC_LARGE;
import static cs3500.threetrios.controller.TestFiles.GRID_CONNECTED_HOLES;

public class TestFullGameConnected {
  private static ModelBase model = new ModelBase();
  private static Referee referee = new RefereeDefault();
  private static Coach red;
  private static Coach blue;
  private static GridCellAbstract[][] state;
  private static Card cardPlaced;

  /**
   * P1Start: Starts the game with the large grid and large card set.
   * Verifies that the current coach is Red and both coaches have 10 cards each.
   */
  @Test
  public void P1Start() {
    model.startGame(
            ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_CONNECTED_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_LARGE)),
            referee);
    red = model.getCurrentCoach();
    Assert.assertEquals("The current coach should be Red.", Coach.Color.Red, red.getColor());

    // Check that Red has 10 cards
    Assert.assertEquals("Red should have 10 cards in hand.", 10, red.getHand().size());

    // Switch to Blue and verify
    blue = model.getCurrentCoach();
    Assert.assertEquals("The current coach should now be Blue.", Coach.Color.Blue, blue.getColor());

    // Check that Blue has 10 cards
    Assert.assertEquals("Blue should have 10 cards in hand.", 10, blue.getHand().size());
  }

  /**
   * P2FirstPlacedCard: Red places the first card on the grid.
   * Verifies that the card is placed correctly without any battles occurring.
   */


}
