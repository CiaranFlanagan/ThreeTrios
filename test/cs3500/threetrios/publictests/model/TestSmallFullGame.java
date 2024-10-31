

package cs3500.threetrios.publictests.model;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.utils.Utils;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static cs3500.threetrios.controller.TestFiles.CC_SMALL;
import static cs3500.threetrios.controller.TestFiles.GRID_NO_HOLES;


/**
 * Tests for the game play and flow of the model.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSmallFullGame {
  // test public beahvior
  private static ThreeTriosModel model = new ThreeTriosModel();
  private static BattlePhaseReferee referee = new DefaultReferee();
  private static Coach red;
  private static Coach blue;
  private static AGridCell[][] state;
  private static Card left;
  // using alphabetical ordering to sequence


  @Test
  public void P1Start() {
    model.startGame(GridConfig.scannerToGrid(Utils.safeFileToScanner(GRID_NO_HOLES)),
            CardConfig.scannerToCardList(Utils.safeFileToScanner(CC_SMALL)), referee);
    // check coach red is actually a red coach
    red = model.getCurrentCoach();
    Assert.assertEquals(red.getColor(), Coach.Color.Red);
  }

  @Test
  public void P2FirstPlacedCard() {
    model.placeCard(0, 0, 0); // red plays  R _ _
    state = model.getGrid().arrayRepr();
    left = state[0][0].getCard(); // this is actual pointer not a copy
    // left has av's of 1
    Assert.assertEquals(left.getCoach().getColor(), Coach.Color.Red);
    // makes sure that if a card is placed with no battle, it stays same color


  }

  @Test
  public void P3SecondPlacedCard() {
    model.nextCoachTurn();
    blue = model.getCurrentCoach();
    Assert.assertEquals(blue.getColor(), Coach.Color.Blue);
    // make sure that we switch coaches properly
    model.placeCard(0, 0, 1); // blue plays so now board is ? B _
    state = model.getGrid().arrayRepr();
    Assert.assertEquals(state[0][1].getCard().getCoach(), blue);
    // left vs mid = 3 < 9 so left loses
    Assert.assertEquals(left.getCoach(), blue); // WE FLIPPED THE CARD!


  }

  @Test
  public void P4LastPlacedCard() {
    model.nextCoachTurn();
    model.placeCard(0, 0, 2); // red plays ? ? R
    state = model.getGrid().arrayRepr();
    // right has av's of A

    // should beat mid and cascade!
    for (AGridCell c : state[0]) {
      Assert.assertTrue(c.hasCard());
      Assert.assertEquals(red, c.getCard().getCoach());
    }
  }

  @Test
  public void P5CheckWin() {
    Assert.assertTrue(model.getGrid().isFull());
    Assert.assertTrue(model.isGameOver());
    Assert.assertSame(model.getWinner(), red);
  }
}
