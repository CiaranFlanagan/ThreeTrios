package cs3500.threetrios.publictests.model;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardCell;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.ThreeTriosModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Tests for the game play and flow of the model.
 */
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
  @Test(expected = IllegalStateException.class)
  public void testStartGameAlreadyStarted() {
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.startGame(gridNoHoles, cardsSmall, referee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullGrid() {
    model.startGame(null, cardsSmall, referee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullCards() {
    model.startGame(gridNoHoles, null, referee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullReferee() {
    model.startGame(gridNoHoles, cardsSmall, null);
  }

  // place card
  @Test(expected = IllegalStateException.class)
  public void testPlaceCardBeforeStart() {
    model.placeCard(0, 0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlaceCardAfterGameOver() {
    AGridCell cc = new CardCell();
    AGridCell[] ccArray = {cc};
    AGridCell[][] singleCell = {ccArray};
    Grid grid = new Grid(singleCell);
    model.startGame(grid, cardsSmall, referee);
    model.placeCard(0, 0, 0);
    model.placeCard(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardInvalidIndex() {
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.placeCard(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardInvalidIndex2() {
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.placeCard(0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardInvalidIndex3() {
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.placeCard(0, 0, -1);
  }

  //isGameOver
  @Test(expected = IllegalStateException.class)
  public void testIsGameOverBeforeStart() {
    model.isGameOver();
  }

  //getWinner
  @Test(expected = IllegalStateException.class)
  public void testGetWinnerBeforeGameIsOver() {
    model.getWinner();
  }

}
