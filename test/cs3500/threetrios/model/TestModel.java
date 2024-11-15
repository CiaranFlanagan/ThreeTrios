package cs3500.threetrios.model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

/**
 * Tests for the ThreeTriosModel class.
 */
public class TestModel {
  private Coach coach;
  private Grid gridNoHoles;
  private List<Card> cardsSmall;
  private ModelBase model;
  private Referee referee;

  @Before
  public void setUp() {
    model = new ModelBase();
    referee = new RefereeDefault();

    gridNoHoles = ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES);
    cardsSmall = ConfigCard.scannerToCardList(TestFiles.CC_SMALL);

    coach = Coach.RED;
    model.startGame(gridNoHoles, cardsSmall, referee);
  }

  @Test
  public void testCurCoachesHand() {
    Map<Coach, List<Card>> hands = model.curCoachesHands();
    assertEquals(2, hands.size());
    assertTrue(hands.containsKey(Coach.RED));
    assertTrue(hands.containsKey(Coach.BLUE));
    assertTrue(hands.get(Coach.RED).isEmpty());
    assertTrue(hands.get(Coach.BLUE).isEmpty());
  }

  @Test
  public void testCurGrid() {
    Grid grid = model.curGrid();
    assertEquals(gridNoHoles, model.curGrid());
    assertSame(model.grid, model.curGrid());
  }

  @Test
  public void testNumRows() {
    assertEquals(gridNoHoles.readOnlyArray2D().length, model.numRows());
  }

  @Test
  public void testNumCols() {
    assertEquals(gridNoHoles.readOnlyArray2D()[0].length, model.numCols());
  }

  @Test
  public void testCardAt() {
    Card testCard = cardsSmall.get(0);
    System.out.println(testCard);
    System.out.println(gridNoHoles);
    gridNoHoles.placeCardOn(0, 0, testCard);
    Optional<Card> card = model.cardAt(0, 0);
    assertTrue(card.isPresent());
    assertEquals(testCard, card.get());
    assertTrue(model.cardAt(0, 1).isEmpty());
  }

  @Test
  public void testOwnerAt() {
    Card testCard = cardsSmall.get(0);
    System.out.println(testCard.getCoach());
    gridNoHoles.placeCardOn(0, 0, testCard);
    Optional<Coach> card = model.ownerAt(0, 0);
    assertTrue(card.isPresent());
    assertEquals(testCard.getCoach(), Coach.RED);
    assertTrue(model.ownerAt(0, 1).isEmpty());
  }

  @Test
  public void testCanPlayAt() {
    Card testCard = cardsSmall.get(0);
    gridNoHoles.placeCardOn(0, 0, testCard);
    assertTrue(model.canPlayAt(0, 1));
    assertFalse(model.canPlayAt(0, 0));
    assertEquals(testCard.getCoach(), Coach.RED);
  }

  @Test
  public void testNumFlippedIfPlaced() {
    Card testCard = Utils.makeCard("WINNER A A A A");
    Assert.assertEquals(model.numFlippedIfPlaced(testCard, 0, 0), 0);
  }

  @Test
  public void testNumFlippedIfPlaced2() {
    List<Card> hand = model.curCoachesHands().get(Coach.RED);
    Card testCard = Utils.makeCard("WINNER A A A A");
    testCard.setCoach(Coach.RED);
    Card testCard2 = Utils.makeCard("LOSER1 2 2 2 2");
    testCard2.setCoach(Coach.BLUE);
    Card testCard3 = Utils.makeCard("LOSER2 1 1 1 1");
    Card placedCard = hand.get(0);
    placedCard.setCoach(Coach.BLUE);
    model.placeCard(0, 0, 1);
    Assert.assertEquals(model.numFlippedIfPlaced(testCard, 0, 0), 1);
  }

  /**
   * bob    1 1 1 1
   * kc     5 5 5 5
   * zeke   A A A A
   * ciaran 1 1 1 1
   */


  @Test
  public void testScore() {
    Card testCard = Utils.makeCard("Ciaran 1 2 3 4");
    Card testCard2 = Utils.makeCard("KC 1 2 3 4");
    Card testCard3 = Utils.makeCard("John 1 2 3 4");
    testCard.setCoach(Coach.RED);
    testCard2.setCoach(Coach.BLUE);
    testCard3.setCoach(Coach.BLUE);
    gridNoHoles.placeCardOn(0, 0, testCard);
    int score = model.score(Coach.RED);
    assertEquals(1, score);
    gridNoHoles.placeCardOn(0, 1, testCard2);
    score = model.score(Coach.BLUE);
    assertEquals(1, score);
    gridNoHoles.placeCardOn(0, 2, testCard3);
    score = model.score(Coach.BLUE);
    assertEquals(2, score);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardAt_InvalidRow() {
    // Test out-of-bounds row index for cardAt
    model.cardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardAt_InvalidColumn() {
    // Test out-of-bounds column index for cardAt
    model.cardAt(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOwnerAt_InvalidRow() {
    // Test out-of-bounds row index for ownerAt
    model.ownerAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOwnerAt_InvalidColumn() {
    model.ownerAt(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCanPlayAt_InvalidRow() {
    model.canPlayAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCanPlayAt_InvalidColumn() {
    model.canPlayAt(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumFlippedIfPlaced_InvalidRow() {
    Card testCard = Utils.makeCard("WINNER A A A A");
    model.numFlippedIfPlaced(testCard, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumFlippedIfPlaced_InvalidColumn() {
    Card testCard = Utils.makeCard("WINNER A A A A");
    model.numFlippedIfPlaced(testCard, 0, -1);
  }

  @Test(expected = NullPointerException.class)
  public void testNumFlippedIfPlaced_NullCard() {
    model.numFlippedIfPlaced(null, 0, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetGridCardAt_InvalidRow() {
    Card testCard = Utils.makeCard("WINNER A A A A");
    model.setGridCardAt(-1, 0, testCard);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGridCardAt_InvalidColumn() {
    Card testCard = Utils.makeCard("WINNER A A A A");
    model.setGridCardAt(0, -1, testCard);
  }


  @Test
  public void testCurCoachesHands() {
    this.model.curCoachesHands();
  }

}

