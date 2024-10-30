package cs3500.threetrios.publictests.model;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.ThreeTriosModel;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static cs3500.threetrios.TestFiles.CC_SMALL;
import static cs3500.threetrios.TestFiles.GRID_NO_HOLES;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSmallFullGame {
  // test public beahvior
  private static ThreeTriosModel model = new ThreeTriosModel();
  private static BattlePhaseReferee referee = new DefaultReferee();
  private static Coach red;
  private static Coach blue;
  private static AGridCell[][] state1;
  private static AGridCell[][] state2;
  private static AGridCell[][] state3;
  private static Card left;
  // using alphabetical ordering to sequence


  @Test
  public void P1Start() {
    model.startGame(GridConfig.fileToGridBoard(GRID_NO_HOLES),
            CardConfig.fileToTTCardList(CC_SMALL), referee);
    System.out.println(model);
    // check coach red is actually a red coach
    red = model.getCurrentCoach();
    Assert.assertEquals(red.getColor(), Coach.Color.Red);
  }

  @Test
  public void P2FirstPlacedCard() {
    System.out.println(model);
    model.placeCard(0, 0, 0); // red plays  R _ _
    System.out.println(model);
    state1 = model.getGrid().arrayRepr();
    left = state1[0][0].getCard(); // this is actual pointer not a copy
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
    model.placeCard(0, 0,  1); // blue plays so now board is ? B _
    state2 = model.getGrid().arrayRepr();
    Assert.assertEquals(state2[0][1].getCard().getCoach(), blue);
    // left vs mid = 3 < 9 so left loses
    Assert.assertEquals(left.getCoach(), blue); // this works


  }

  @Test
  public void P4LastPlacedCard() {
    model.nextCoachTurn();
    model.placeCard(0, 0, 2); // red plays ? ? R
    state3 = model.getGrid().arrayRepr();
    // right has av's of A

    // should beat mid and cascade!
    for (AGridCell c : state3[0]) {
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
