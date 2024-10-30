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
import org.junit.runner.OrderWith;

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

}
