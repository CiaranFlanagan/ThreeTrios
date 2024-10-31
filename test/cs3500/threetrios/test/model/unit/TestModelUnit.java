package cs3500.threetrios.test.model.unit;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.GridCellCard;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.ModelBase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Tests for the game play and flow of the model.
 */
public class TestModelUnit {
  // test public beahvior
  private Grid gridNoHoles;
  private Grid gridConnectedHoles;
  private Grid gridDisconnectedHoles;
  private List<Card> cardsSmall;
  private List<Card> cardsLarge;
  private ModelBase model;
  private Referee referee;

  @Before
  public void setUp() {
    model = new ModelBase();
    referee = new RefereeDefault();
    try {
      gridNoHoles = ConfigGrid.scannerToGrid(new Scanner(TestFiles.GRID_NO_HOLES));
      //gridConnectedHoles = GridConfig.scannerToGrid(null);
      // gridDisconnectedHoles = GridConfig.scannerToGrid(fillInHere));

      cardsSmall = ConfigCard.scannerToCardList(new Scanner(TestFiles.CC_SMALL));
      cardsLarge = ConfigCard.scannerToCardList(new Scanner(TestFiles.CC_LARGE));
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
    GridCellAbstract cc = new GridCellCard();
    GridCellAbstract[] ccArray = {cc};
    GridCellAbstract[][] singleCell = {ccArray};
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

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNotEnoughCards() {
    model.startGame(gridNoHoles, cardsSmall.subList(0, 1), referee);
  }

}
