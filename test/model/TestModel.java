package model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import utils.ConfigCard;
import utils.ConfigGrid;
import utils.TestFiles;
import utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ThreeTriosModel class.
 */
public class TestModel {

  CoachColor coach;
  private Grid gridNoHoles;
  private List<Card> cardsSmall;
  private ModelBase model;

  @Before
  public void setUp() {
    model = new ModelBase();
    Referee referee = new RefereeDefault();

    gridNoHoles = ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES);
    cardsSmall = ConfigCard.scannerToCardList(TestFiles.CC_SMALL);

    CoachColor coach = CoachColor.RED;
    model.startGame(gridNoHoles, cardsSmall, referee);
  }

  @Test
  public void testCurCoachesHand() {
    Map<CoachColor, List<Card>> hands = model.curCoachesHands();
    assertEquals(2, hands.size());
    assertTrue(hands.containsKey(CoachColor.RED));
    assertTrue(hands.containsKey(CoachColor.BLUE));
    assertFalse(hands.get(CoachColor.RED).isEmpty());
    assertFalse(hands.get(CoachColor.BLUE).isEmpty());
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
    gridNoHoles.placeCardOn(0, 0, testCard);
    Optional<Card> card = model.cardAt(0, 0);
    assertTrue(card.isPresent());
    assertEquals(testCard, card.get());
    assertTrue(model.cardAt(0, 1).isEmpty());
  }

  @Test
  public void testOwnerAt() {
    Card testCard = cardsSmall.get(0);
    gridNoHoles.placeCardOn(0, 0, testCard);
    Optional<CoachColor> card = model.ownerAt(0, 0);
    assertTrue(card.isPresent());
    assertEquals(testCard.getCoach(), CoachColor.RED);
    assertTrue(model.ownerAt(0, 1).isEmpty());
  }

  @Test
  public void testCanPlayAt() {
    Card testCard = cardsSmall.get(0);
    gridNoHoles.placeCardOn(0, 0, testCard);
    assertTrue(model.canPlayAt(0, 1));
    assertFalse(model.canPlayAt(0, 0));
    assertEquals(testCard.getCoach(), CoachColor.RED);
  }

  @Test
  public void testNumFlippedIfPlaced() {
    Card testCard = TestUtils.makeCard("WINNER A A A A");
    Assert.assertEquals(model.numFlippedIfPlaced(testCard, 0, 0), 0);
  }

  @Test
  public void testNumFlippedIfPlaced2() {
    Card testCard = TestUtils.makeCard("WINNER A A A A");
    model.placeCard(0, 0, 1);
    Assert.assertEquals(model.numFlippedIfPlaced(testCard, 0, 0), 1);
  }

  @Test
  public void testScore() {
    Card testCard = TestUtils.makeCard("Ciaran 1 2 3 4");
    Card testCard2 = TestUtils.makeCard("KC 1 2 3 4");
    Card testCard3 = TestUtils.makeCard("John 1 2 3 4");
    testCard.setCoach(CoachColor.RED);
    testCard2.setCoach(CoachColor.BLUE);
    testCard3.setCoach(CoachColor.BLUE);
    gridNoHoles.placeCardOn(0, 0, testCard);
    int score = model.score(CoachColor.RED);
    assertEquals(1, score);
    gridNoHoles.placeCardOn(0, 1, testCard2);
    score = model.score(CoachColor.BLUE);
    assertEquals(1, score);
    gridNoHoles.placeCardOn(0, 2, testCard3);
    score = model.score(CoachColor.BLUE);
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
    Card testCard = TestUtils.makeCard("WINNER A A A A");
    model.numFlippedIfPlaced(testCard, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumFlippedIfPlaced_InvalidColumn() {
    Card testCard = TestUtils.makeCard("WINNER A A A A");
    model.numFlippedIfPlaced(testCard, 0, -1);
  }

  @Test(expected = NullPointerException.class)
  public void testNumFlippedIfPlaced_NullCard() {
    model.numFlippedIfPlaced(null, 0, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetGridCardAt_InvalidRow() {
    Card testCard = TestUtils.makeCard("WINNER A A A A");
    model.setGridCardAt(-1, 0, testCard);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGridCardAt_InvalidColumn() {
    Card testCard = TestUtils.makeCard("WINNER A A A A");
    model.setGridCardAt(0, -1, testCard);
  }

  static class GridCellMock extends GridCellAbstract {

    @Override
    public boolean hasCard() {
      return false;
    }

    @Override
    public void acceptBattlePhase(Referee battlePhase) {
      throw new IllegalStateException("not impl");
    }

    public boolean hasNeighborIn(CardinalDirection dir) {
      return getNeighborToThe(dir) != null;
    }

  }

  /**
   * Tests for the Grid class.
   */
  public static class TestGrid {

    private Grid grid;

    @Before
    public void setUp() {
      CellType[][] twobytwoallcards = new CellType[2][2];
      twobytwoallcards[0][0] = CellType.CARD;
      twobytwoallcards[0][1] = CellType.CARD;
      twobytwoallcards[1][0] = CellType.CARD;
      twobytwoallcards[1][1] = CellType.CARD;
      grid = new Grid(twobytwoallcards);
    }

    @Test
    public void testPlaceCardOn() {
      HashMap<Object, Object> hm = new HashMap<>();
      Card card = TestUtils.makeCard("a 1 1 1 1");
      GridCellAbstract cell = grid.placeCardOn(0, 0, card);
      assertTrue(cell.hasCard());
      assertEquals(card.toString(), cell.getCard().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceCardOnInvalidPositionSmallRow() {
      Card card = new Card("TestCard", new HashMap<>());
      grid.placeCardOn(-1, 0, card);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceCardOnInvalidPositionLargeRow() {
      Card card = new Card("TestCard", new HashMap<>());
      grid.placeCardOn(2, 0, card);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceCardOnInvalidPositionSmallCol() {
      Card card = new Card("TestCard", new HashMap<>());
      grid.placeCardOn(0, -1, card);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceCardOnInvalidPositionLargeCol() {
      Card card = new Card("TestCard", new HashMap<>());
      grid.placeCardOn(0, 2, card);
    }

    @Test
    public void testIsFull() {
      assertFalse(grid.isFull());
      Card card = new Card("TestCard", new HashMap<>());
      grid.placeCardOn(0, 0, card);
      grid.placeCardOn(0, 1, card);
      grid.placeCardOn(1, 0, card);
      grid.placeCardOn(1, 1, card);
      assertTrue(grid.isFull());
    }

    @Test
    public void testCopy() {
      Grid grid = this.grid;
      grid.placeCardOn(0, 0, TestUtils.makeCard("a 2 1 1 1"));
      Grid gridCopy = grid.copy();
      assertEquals(getCard(grid, 0, 0), getCard(gridCopy, 0, 0));
    }

    private Card getCard(Grid grid, int x, int y) {
      return grid.readOnlyArray2D()[x][y].getCard();
    }


  }

  /**
   * Tests for the GridCellCard class.
   */
  public static class TestGridCellHole {

    private GridCellAbstract cell1;
    private GridCellAbstract cell2;
    private GridCellAbstract cell3;
    private GridCellAbstract cell4;

    @Before
    public void setUp() {
      cell1 = new GridCellHole();
      cell2 = new GridCellHole();
      cell3 = new GridCellHole();
      cell4 = new GridCellHole();
      cell1.link(cell2, CardinalDirection.EAST);
      cell3.link(cell4, CardinalDirection.NORTH);
    }

    //need linked cells so even through is public, it in this class
    @Test
    public void testHasNeighborToThe() {
      assertTrue(cell1.hasNeighborToThe(CardinalDirection.EAST));
    }

    //need linked cells so even through is public, it in this class
    @Test
    public void testGetNeighborToThe() {
      cell1.getNeighborToThe(CardinalDirection.EAST);
      assertEquals(cell2, cell1.getNeighborToThe(CardinalDirection.EAST));
    }

    @Test
    public void testLink() {
      cell1.link(cell2, CardinalDirection.WEST);
      assertEquals(cell2, cell1.getNeighborToThe(CardinalDirection.WEST));
      assertEquals(cell1, cell2.getNeighborToThe(CardinalDirection.EAST));
      assertEquals(cell3, cell4.getNeighborToThe(CardinalDirection.SOUTH));
      assertEquals(cell4, cell3.getNeighborToThe(CardinalDirection.NORTH));
    }

    @Test(expected = IllegalStateException.class)
    public void testPlaceCard() {
      cell1.placeCard(new Card("Red", new HashMap<>()));
    }

    @Test
    public void testRenderTextConstructor() {
      assertEquals("X", cell1.renderTextConstructor());
    }

    @Test(expected = IllegalStateException.class)
    public void testAcceptBattlePhase() {
      Referee ref = new RefereeDefault();
      cell1.acceptBattlePhase(ref);
    }

  }

  /**
   * Tests for the GridCellCard class.
   */
  public static class TestGridCellCard {

    private GridCellAbstract cell1;
    private GridCellAbstract cell2;
    private GridCellAbstract cell3;
    private GridCellAbstract cell4;

    @Before
    public void setUp() {
      cell1 = new GridCellCard();
      cell2 = new GridCellCard();
      cell3 = new GridCellCard();
      cell4 = new GridCellCard();
      cell1.link(cell2, CardinalDirection.EAST);
      cell3.link(cell4, CardinalDirection.NORTH);
    }

    //need linked cells so even through is public, it in this class
    @Test
    public void testHasNeighborToThe() {
      assertTrue(cell1.hasNeighborToThe(CardinalDirection.EAST));
    }

    //need linked cells so even through is public, it in this class
    @Test
    public void testGetNeighborToThe() {
      cell1.getNeighborToThe(CardinalDirection.EAST);
      assertEquals(cell2, cell1.getNeighborToThe(CardinalDirection.EAST));
    }

    @Test
    public void testLink() {
      cell1.link(cell2, CardinalDirection.WEST);
      assertEquals(cell2, cell1.getNeighborToThe(CardinalDirection.WEST));
      assertEquals(cell1, cell2.getNeighborToThe(CardinalDirection.EAST));
      assertEquals(cell3, cell4.getNeighborToThe(CardinalDirection.SOUTH));
      assertEquals(cell4, cell3.getNeighborToThe(CardinalDirection.NORTH));
    }

    //public but must be tested here because it used protected methods
    @Test
    public void testPlaceCard() {
      Card c = TestUtils.makeCard("b 1 2 3 4");
      cell1.placeCard(c);
      assertEquals(c, cell1.getCard());
    }

    @Test(expected = IllegalStateException.class)
    public void testPlaceCardTwice() {
      Card c = new Card("Red", new HashMap<>());
      cell1.placeCard(c);
      cell1.placeCard(c);
    }

    @Test
    public void testRenderTextConstructor() {
      assertEquals("C", cell1.renderTextConstructor());
    }

    @Test
    public void testAcceptBattlePhase() {
      Referee ref = new RefereeDefault();
      try {
        new GridCellCard().acceptBattlePhase(ref);
      } catch (Exception ignored) {
        Assert.fail("shouldn't throw exception");
      }

    }

  }

  /**
   * Tests for the BoardCell class.
   */
  public static class TestBoardCell {

    @Test
    public void testLink1() {
      GridCellMock s = new GridCellMock();
      GridCellMock n = new GridCellMock();
      s.link(n, CardinalDirection.NORTH);
      assertTrue(s.hasNeighborIn(CardinalDirection.NORTH));
      assertTrue(n.hasNeighborIn(CardinalDirection.SOUTH));

      GridCellMock e = new GridCellMock();
      GridCellMock w = new GridCellMock();
      e.link(w, CardinalDirection.WEST);
      assertTrue(e.hasNeighborIn(CardinalDirection.WEST));
      assertTrue(w.hasNeighborIn(CardinalDirection.EAST));
    }

  }

  /**
   * Tests for protected and package private methods in the model.
   */
  public static class TestCard {

    private Card card;

    @Before
    public void setUp() {
      Map<CardinalDirection, AttackValue> attackValues = new HashMap<>();
      attackValues.put(CardinalDirection.NORTH, AttackValue.FIVE);
      attackValues.put(CardinalDirection.SOUTH, AttackValue.THREE);
      attackValues.put(CardinalDirection.EAST, AttackValue.FOUR);
      attackValues.put(CardinalDirection.WEST, AttackValue.TWO);
      card = new Card("TestCard", attackValues);
    }

    @Test
    public void testGetName() {
      assertEquals("TestCard", card.getName());
    }

    @Test
    public void testGetAttackValue() {
      assertEquals(AttackValue.FIVE, card.getAttackValue(CardinalDirection.NORTH));
      assertEquals(AttackValue.THREE, card.getAttackValue(CardinalDirection.SOUTH));
      assertEquals(AttackValue.FOUR, card.getAttackValue(CardinalDirection.EAST));
      assertEquals(AttackValue.TWO, card.getAttackValue(CardinalDirection.WEST));
    }


    @Test
    public void testBeats() {
      Map<CardinalDirection, AttackValue> otherAttackValues = new HashMap<>();
      otherAttackValues.put(CardinalDirection.NORTH, AttackValue.ONE);
      otherAttackValues.put(CardinalDirection.SOUTH, AttackValue.ONE);
      otherAttackValues.put(CardinalDirection.EAST, AttackValue.ONE);
      otherAttackValues.put(CardinalDirection.WEST, AttackValue.ONE);
      Card otherCard = new Card("OtherCard", otherAttackValues);

      assertTrue(card.beats(otherCard, CardinalDirection.NORTH));
      assertTrue(card.beats(otherCard, CardinalDirection.SOUTH));
      assertTrue(card.beats(otherCard, CardinalDirection.EAST));
      assertTrue(card.beats(otherCard, CardinalDirection.WEST));
    }

    @Test
    public void testGetCoach() {
      CoachColor coachColor = CoachColor.RED;
      card.setCoach(coachColor);
      assertEquals(CoachColor.RED, card.getCoach());
    }

    @Test
    public void testSetAttackValueInDirection() {
      card.setAttackValueInDirection(AttackValue.ONE, CardinalDirection.NORTH);
      assertEquals(AttackValue.ONE, card.getAttackValue(CardinalDirection.NORTH));
    }

    @Test
    public void testSetCoach() {
      CoachColor coachColor = CoachColor.RED;
      card.setCoach(coachColor);
      assertEquals(CoachColor.RED, card.getCoach());
    }

  }

  /**
   * Tests for the Referee class.
   */
  public static class TestReferee {

    private GridCellHole holeCell1;
    private GridCellHole holeCell2;
    private GridCellAbstract cardCell1;
    private GridCellAbstract cardCell2;
    private GridCellAbstract cardCell3;
    private CoachColor kc;
    private CoachColor ciaran;
    private Card card1;
    private Card card2;
    private Card card3;
    private RefereeDefault ref;

    @Before
    public void setup() {
      holeCell1 = new GridCellHole();
      holeCell2 = new GridCellHole();
      cardCell1 = new GridCellCard();
      cardCell2 = new GridCellCard();
      cardCell3 = new GridCellCard();
      kc = CoachColor.RED;
      ciaran = CoachColor.BLUE;
      card1 = TestUtils.makeCard("c1 1 2 3 4");
      card2 = TestUtils.makeCard("c2 5 6 7 8");
      card3 = TestUtils.makeCard("c3 A A A A");
      ref = new RefereeDefault();
    }

    @Test(expected = IllegalStateException.class)
    public void testRef1() {

      holeCell1.link(holeCell1, CardinalDirection.NORTH);
      ref.refereeBattlePhase(holeCell1);
    }

    @Test(expected = IllegalStateException.class)
    public void testRef2() {
      holeCell2.link(cardCell1, CardinalDirection.NORTH);
      ref.refereeBattlePhase(holeCell2);
    }

    @Test
    public void testRef3() {
      holeCell2.link(cardCell2, CardinalDirection.EAST);
      cardCell2.placeCard(card1);
      card1.setCoach(kc);
      ref.refereeBattlePhase(cardCell2);
      assertEquals(holeCell2.canHaveCard(), false);
      assertEquals(cardCell2.getCard().getCoach(), kc);
    }

    @Test
    public void testRef4() {
      card1.setCoach(kc); // this should change after battle
      card2.setCoach(ciaran);
      cardCell1.link(cardCell2, CardinalDirection.NORTH);
      cardCell1.placeCard(card1);
      cardCell2.placeCard(card2);
      ref.refereeBattlePhase(cardCell2);
      assertSame(ciaran, card2.getCoach());
      assertSame(ciaran, card1.getCoach());
    }

    @Test
    public void testRef5() {
      //
      //  C1
      //  |
      //  C2 - C3

      card1.setCoach(kc); // this should change after battle
      card2.setCoach(ciaran);
      card3.setCoach(kc);
      cardCell1.link(cardCell2, CardinalDirection.SOUTH);
      cardCell2.link(cardCell3, CardinalDirection.EAST);
      cardCell1.placeCard(card1);
      cardCell2.placeCard(card2);
      cardCell3.placeCard(card3);
      ref.refereeBattlePhase(cardCell3);
      assertSame(kc, card3.getCoach());
      assertSame(kc, card2.getCoach());
      assertSame(kc, card1.getCoach());
    }

    @Test
    public void testRef6() {
      // no hacks
      holeCell1.link(cardCell1, CardinalDirection.WEST);
      holeCell1.link(cardCell2, CardinalDirection.EAST);
      cardCell1.placeCard(card1);
      cardCell2.placeCard(card2);
      card1.setCoach(kc);
      card2.setCoach(ciaran);
      ref.refereeBattlePhase(cardCell2);
      // card 2 would usually beat card1 but they should be different colors because of
      // hole
      Assert.assertNotEquals(card1.getCoach(), card2.getCoach());

    }

    @Test
    public void testNegate() {
      // to completely test:
      // test that it turns a true into a false
      // test that it turns a false into a true

      // trivially, test all directions
      Referee refDefault = new RefereeDefault();
      Referee negate = new RefereeNegate();
      Card allones = TestUtils.makeCard("ones 1 1 1 1");
      Card alltwos = TestUtils.makeCard("twos 2 2 2 2");
      for (CardinalDirection dir : CardinalDirection.values()) {
        assertTrue(negate.fightCardsAcc(allones, alltwos, dir,
            refDefault.fightCardsAcc(allones, alltwos, dir, false)));
        assertFalse(negate.fightCardsAcc(allones, alltwos, dir,
            refDefault.fightCardsAcc(alltwos,allones, dir, false)));
      }

    }

    @Test
    public void testFallenAce() {
      Referee fallenAce = new RefereeFallenAce();
      Card allones = TestUtils.makeCard("ones 1 1 1 1");
      Card allaces = TestUtils.makeCard("aces A A A A");
      for (CardinalDirection dir : CardinalDirection.values()) {
        assertTrue(fallenAce.fightCardsAcc(allones, allaces, dir, false));
      }

      for (CardinalDirection dir : CardinalDirection.values()) {
        assertFalse(fallenAce.fightCardsAcc(allaces, allones, dir, false));
      }
    }

    @Test
    public void testCompose() {
      // interesting compositions
      // two negates should result in same behavior
      List<Card> cards = ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
      // test the compose method
      Referee negate = new RefereeNegate();
      Referee rDefault = new RefereeDefault();
      for (int outerIdx = 0; outerIdx < cards.size(); outerIdx++) {
        for (int innerIdx = 0; innerIdx < cards.size(); innerIdx++) {
          for (CardinalDirection dir : CardinalDirection.values()) {
            Card fst = cards.get(outerIdx);
            Card snd = cards.get(innerIdx);
            assertEquals(negate.fightCardsAcc(
                    fst, snd, dir, negate.fightCardsAcc(fst, snd, dir,
                        rDefault.fightCardsAcc(fst, snd, dir, false))),
                rDefault.fightCardsAcc(fst, snd, dir, false));
          }
        }
      }

      // test the compose class
      Referee rdoubleneg = new RefComposeBeats(List.of(new RefereeDefault(), new RefereeNegate(),
          new RefereeNegate()));

      for (int outerIdx = 0; outerIdx < cards.size(); outerIdx++) {
        for (int innerIdx = 0; innerIdx < cards.size(); innerIdx++) {
          for (CardinalDirection dir : CardinalDirection.values()) {
            Card fst = cards.get(outerIdx);
            Card snd = cards.get(innerIdx);
            assertEquals(rdoubleneg.fightCardsAcc(fst, snd, dir, false),
                rDefault.fightCardsAcc(fst, snd, dir, false));
          }
        }
      }

      Referee risenAce = new RefComposeBeats(List.of(new RefereeFallenAce(), new RefereeNegate()));

      Card allones = TestUtils.makeCard("ones 1 1 1 1");
      Card alltwos = TestUtils.makeCard("twos 2 2 2 2");
      Card allaces = TestUtils.makeCard("aces A A A A");

      //TODO
      // test that 1 beats 2
      // test that A beats 1
      // using the above methods testNegate and testCompose
      // fill-in classes fightCardsAcc methods

      for (CardinalDirection dir : CardinalDirection.values()) {
        assertTrue(negate.fightCardsAcc(allones, alltwos, dir, risenAce.fightCardsAcc(allones,
            alltwos, dir, false)));
      }

      for (CardinalDirection dir : CardinalDirection.values()) {
        assertTrue(negate.fightCardsAcc(allones, alltwos, dir, risenAce.fightCardsAcc(allaces,
            allones, dir, false)));
      }

      // TO BUILD JAR: preferences -> build -> build project
    }


  }

}
