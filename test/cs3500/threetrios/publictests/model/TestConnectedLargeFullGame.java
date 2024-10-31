package cs3500.threetrios.publictests.model;

import org.junit.Assert;
import org.junit.Test;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.utils.Utils;

import static cs3500.threetrios.controller.TestFiles.CC_LARGE;
import static cs3500.threetrios.controller.TestFiles.GRID_CONNECTED_HOLES;

public class TestConnectedLargeFullGame {
  private static ThreeTriosModel model = new ThreeTriosModel();
  private static BattlePhaseReferee referee = new DefaultReferee();
  private static Coach red;
  private static Coach blue;
  private static AGridCell[][] state;
  private static Card cardPlaced;

  /**
   * P1Start: Starts the game with the large grid and large card set.
   * Verifies that the current coach is Red and both coaches have 10 cards each.
   */
  @Test
  public void P1Start() {
    model.startGame(
            GridConfig.scannerToGrid(Utils.safeFileToScanner(GRID_CONNECTED_HOLES)),
            CardConfig.scannerToCardList(Utils.safeFileToScanner(CC_LARGE)),
            referee);
    red = model.getCurrentCoach();
    Assert.assertEquals("The current coach should be Red.", Coach.Color.Red, red.getColor());

    // Check that Red has 10 cards
    Assert.assertEquals("Red should have 10 cards in hand.", 10, red.getHand().size());

    // Switch to Blue and verify
    model.nextCoachTurn();
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
