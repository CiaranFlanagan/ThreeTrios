package cs3500.threetrios.publictests.model;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.ThreeTriosModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestModel {
  // test public beahvior
  private Grid gridNoHoles;
  private Grid gridConnectedHoles;
  private Grid gridDisconnectedHoles;
  private List<Card> cardsSmall;
  private List<Card> cardsLarge;
  private ThreeTriosModel model;
  private BattlePhaseReferee referee;
  @Before
  public void setUp() {
    model = new ThreeTriosModel();
    referee = new DefaultReferee();

    gridNoHoles = GridConfig.fileToGridBoard(new File("./test/grid_no_holes.txt"));
    gridConnectedHoles = GridConfig.fileToGridBoard(new File("./test/grid_connected_holes.txt"));
    gridDisconnectedHoles = GridConfig.fileToGridBoard(new File("./test/grid_disconnected_holes.txt"));

    cardsSmall = CardConfig.fileToTTCardList(new File("./test/cards_small.txt"));
    cardsLarge = CardConfig.fileToTTCardList(new File("./test/cards_large.txt"));
  }


  // test startgame
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameAlreadyStarted() {
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.startGame(gridNoHoles, cardsSmall, referee);
  }
  // place card

  // getcurCoach

  // nextTurn()

  // isgameover

  // get winner

  // get grid

  // integration

  @Test
  public void integrationTest1() {
    System.out.println(cardsSmall);
    Coach red;
    Coach blue;
    model.startGame(gridNoHoles, cardsSmall, referee);
    // check coach red is actually a red coach
    red = model.getCurrentCoach();
    Assert.assertEquals(red.getColor(), Coach.Color.Red);

    model.placeCard(0, 0, 0); // red plays  R _ _
    AGridCell[][] state1 = model.getGrid().arrayRepr();
    Card left = state1[0][0].getCard(); // this is actual pointer not a copy
    // left has av's of 1
    Assert.assertEquals(left.getCoach().getColor(), Coach.Color.Red);
    // makes sure that if a card is placed with no battle, it stays same color

    model.nextCoachTurn();
    blue = model.getCurrentCoach();
    Assert.assertEquals(blue.getColor(), Coach.Color.Blue);
    // make sure that we switch coaches properly

    model.placeCard(0, 0,  1); // blue plays so now board is ? B _
    AGridCell[][] state2 = model.getGrid().arrayRepr();
    Assert.assertEquals(state2[0][1].getCard().getCoach(), blue);
    Card mid = state2[0][1].getCard();
    System.out.println(left.getCoach());
    System.out.println(left.getAttackValue(CardinalDirection.EAST));
    System.out.println(mid.getAttackValue(CardinalDirection.WEST));
    // mid has av's of 5
    System.out.println(left);
    System.out.println(mid);
    // left vs mid = 3 < 9 so left loses
    Assert.assertEquals(left.getCoach(), blue); // this works

    model.nextCoachTurn();
    model.placeCard(0, 0, 2); // red plays ? ? R
    AGridCell[][] state3 = model.getGrid().arrayRepr();
    Card right = state3[0][2].getCard();
    // right has av's of A

    // should beat mid and cascade!
    for (AGridCell c : state3[0]) {
      System.out.print(c);
    }
    for (AGridCell c : state3[0]) {
      Assert.assertTrue(c.hasCard());
      Assert.assertEquals(red, c.getCard().getCoach());
    }

    Assert.assertTrue(model.getGrid().isFull());
    Assert.assertTrue(model.isGameOver());
    Assert.assertSame(model.getWinner(), red);
  }
}
