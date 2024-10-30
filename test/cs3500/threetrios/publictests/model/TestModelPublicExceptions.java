package cs3500.threetrios.publictests.model;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.ThreeTriosModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class TestModelPublicExceptions {
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
    try {
      gridNoHoles = GridConfig.scannerToGrid(new Scanner(TestFiles.GRID_NO_HOLES));
      //gridConnectedHoles = GridConfig.scannerToGrid(null);
      // gridDisconnectedHoles = GridConfig.scannerToGrid(fillInHere));

      cardsSmall = CardConfig.scannerToCardList(new Scanner(TestFiles.CC_SMALL));
      cardsLarge = CardConfig.scannerToCardList(new Scanner(TestFiles.CC_LARGE));
    } catch (FileNotFoundException ex) {
      Assert.fail("file not found");
    }

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
