package test;

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.TestFiles;
import model.AttackValue;
import model.Card;
import model.CardinalDirection;
import model.CellType;
import model.Coach;
import model.Grid;
import model.GridCellCard;
import model.GridCellHole;
import model.GridCellReadOnly;
import model.ModelBase;
import model.Referee;
import model.RefereeDefault;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.TestFiles.CC_SMALL;
import static utils.TestFiles.GRID_NO_HOLES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the game play and flow of the model.
 */
@RunWith(Enclosed.class)
public class TestModel {

  // test public beahvior
  private Grid gridNoHoles;
  private Grid gridConnectedHoles;
  private Grid gridDisconnectedHoles;
  private List<Card> cardsSmall;
  private ModelBase model;
  private Referee referee;

  @Before
  public void setUp() {
    model = new ModelBase();
    referee = new RefereeDefault();

    gridNoHoles = ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES);
    //gridConnectedHoles = GridConfig.scannerToGrid(null);
    // gridDisconnectedHoles = GridConfig.scannerToGrid(fillInHere));

    cardsSmall = ConfigCard.scannerToCardList(TestFiles.CC_SMALL);
    List<Card> cardsLarge = ConfigCard.scannerToCardList(TestFiles.CC_LARGE);

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
    CellType[][] onebyone = new CellType[][]{{CellType.CARD}};
    Grid grid = new Grid(onebyone);
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
    model.winner();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNotEnoughCards() {
    model.startGame(gridNoHoles, cardsSmall.subList(0, 1), referee);
  }


  /**
   * Tests for the AttackValue enum.
   */
  public static class TestAttackValue {

    /**
     * P1_FromString_ValidInputs: Tests the fromString method with all valid inputs.
     */
    @Test
    public void TestFromString_ValidInputs() {
      Assert.assertEquals("1 => ONE",
                          AttackValue.ONE, AttackValue.fromString("1"));
      Assert.assertEquals("2 => TWO",
                          AttackValue.TWO, AttackValue.fromString("2"));
      Assert.assertEquals("3 => THREE",
                          AttackValue.THREE, AttackValue.fromString("3"));
      Assert.assertEquals("4 => FOUR",
                          AttackValue.FOUR, AttackValue.fromString("4"));
      Assert.assertEquals("5 => FIVE",
                          AttackValue.FIVE, AttackValue.fromString("5"));
      Assert.assertEquals("6 => SIX",
                          AttackValue.SIX, AttackValue.fromString("6"));
      Assert.assertEquals("7 => SEVEN",
                          AttackValue.SEVEN, AttackValue.fromString("7"));
      Assert.assertEquals("8 => EIGHT",
                          AttackValue.EIGHT, AttackValue.fromString("8"));
      Assert.assertEquals("9 => NINE",
                          AttackValue.NINE, AttackValue.fromString("9"));
      Assert.assertEquals("10 => A",
                          AttackValue.A, AttackValue.fromString("A"));
    }

    /**
     * P2_FromString_InvalidInputs: Tests the fromString method with invalid inputs,
     * expecting IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestFromStringInvalidInput_B() {
      AttackValue.fromString("B");
    }

    /**
     * P7_FromAttackValue: Tests the fromAttackValue method for all enum constants.
     */
    @Test
    public void TestFromAttackValue() {
      Assert.assertEquals("ONE should convert to 1", 1, AttackValue.ONE.fromAttackValue());
      Assert.assertEquals("TWO should convert to 2", 2, AttackValue.TWO.fromAttackValue());
      Assert.assertEquals("THREE should convert to 3", 3, AttackValue.THREE.fromAttackValue());
      Assert.assertEquals("FOUR should convert to 4", 4, AttackValue.FOUR.fromAttackValue());
      Assert.assertEquals("FIVE should convert to 5", 5, AttackValue.FIVE.fromAttackValue());
      Assert.assertEquals("SIX should convert to 6", 6, AttackValue.SIX.fromAttackValue());
      Assert.assertEquals("SEVEN should convert to 7", 7, AttackValue.SEVEN.fromAttackValue());
      Assert.assertEquals("EIGHT should convert to 8", 8, AttackValue.EIGHT.fromAttackValue());
      Assert.assertEquals("NINE should convert to 9", 9, AttackValue.NINE.fromAttackValue());
      Assert.assertEquals("A should convert to 10", 10, AttackValue.A.fromAttackValue());
    }

    @Test
    public void TestBeats() {
      // ONE beats nothing
      Assert.assertFalse("ONE should not beat ONE", AttackValue.ONE.beats(AttackValue.ONE));
      Assert.assertFalse("ONE should not beat TWO", AttackValue.ONE.beats(AttackValue.TWO));

      // TWO beats ONE but not THREE
      Assert.assertTrue("TWO should beat ONE", AttackValue.TWO.beats(AttackValue.ONE));
      Assert.assertFalse("TWO should not beat THREE", AttackValue.TWO.beats(AttackValue.THREE));

      // THREE beats TWO but not FOUR
      Assert.assertTrue("THREE should beat TWO", AttackValue.THREE.beats(AttackValue.TWO));
      Assert.assertFalse("THREE should not beat FOUR", AttackValue.THREE.beats(AttackValue.FOUR));

      // Continue similarly up to A
      Assert.assertTrue("FOUR should beat THREE", AttackValue.FOUR.beats(AttackValue.THREE));
      Assert.assertFalse("FOUR should not beat FIVE", AttackValue.FOUR.beats(AttackValue.FIVE));

      Assert.assertTrue("FIVE should beat FOUR", AttackValue.FIVE.beats(AttackValue.FOUR));
      Assert.assertFalse("FIVE should not beat SIX", AttackValue.FIVE.beats(AttackValue.SIX));

      Assert.assertTrue("SIX should beat FIVE", AttackValue.SIX.beats(AttackValue.FIVE));
      Assert.assertFalse("SIX should not beat SEVEN", AttackValue.SIX.beats(AttackValue.SEVEN));

      Assert.assertTrue("SEVEN should beat SIX", AttackValue.SEVEN.beats(AttackValue.SIX));
      Assert.assertFalse("SEVEN should not beat EIGHT", AttackValue.SEVEN.beats(AttackValue.EIGHT));

      Assert.assertTrue("EIGHT should beat SEVEN", AttackValue.EIGHT.beats(AttackValue.SEVEN));
      Assert.assertFalse("EIGHT should not beat NINE", AttackValue.EIGHT.beats(AttackValue.NINE));

      Assert.assertTrue("NINE should beat EIGHT", AttackValue.NINE.beats(AttackValue.EIGHT));
      Assert.assertFalse("NINE should not beat A", AttackValue.NINE.beats(AttackValue.A));

      Assert.assertTrue("A should beat NINE", AttackValue.A.beats(AttackValue.NINE));
      Assert.assertFalse("A should not beat A", AttackValue.A.beats(AttackValue.A));
    }

    /**
     * P9_ToString: Tests the toString method for all enum constants.
     */
    @Test
    public void TestToString() {
      Assert.assertEquals("2", AttackValue.TWO.toString());
      Assert.assertEquals("3", AttackValue.THREE.toString());
      Assert.assertEquals("4", AttackValue.FOUR.toString());
      Assert.assertEquals("5", AttackValue.FIVE.toString());
      Assert.assertEquals("6", AttackValue.SIX.toString());
      Assert.assertEquals("7", AttackValue.SEVEN.toString());
      Assert.assertEquals("8", AttackValue.EIGHT.toString());
      Assert.assertEquals("9", AttackValue.NINE.toString());
      Assert.assertEquals("A", AttackValue.A.toString());
    }

  }

  /**
   * Tests for protected and package private methods in the model.
   */
  public static class TestCard {

    private Card card;
    private Map<CardinalDirection, AttackValue> attackValues;

    @Before
    public void setUp() {
      attackValues = new HashMap<>();
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
    public void testToString() {
      String expected = "TestCard 5 3 4 2";
      assertEquals(expected, card.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
      new Card(null, attackValues);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAttackValues() {
      new Card("TestCard", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
      new Card("", attackValues);
    }

  }

  /**
   * Tests for the CardinalDirection class.
   */
  public static class TestCardinalDirection {

    @Before
    public void setUp() {
      CardinalDirection dirNorth = CardinalDirection.NORTH;
      CardinalDirection dirSouth = CardinalDirection.SOUTH;
      CardinalDirection dirEast = CardinalDirection.EAST;
      CardinalDirection dirWest = CardinalDirection.WEST;
    }

    @Test
    public void TestOppositeValidDirections() {
      assertEquals(CardinalDirection.NORTH.opposite(), CardinalDirection.SOUTH);
      assertEquals(CardinalDirection.SOUTH.opposite(), CardinalDirection.NORTH);
      assertEquals(CardinalDirection.EAST.opposite(), CardinalDirection.WEST);
      assertEquals(CardinalDirection.WEST.opposite(), CardinalDirection.EAST);
    }

  }

  /**
   * Tests for the Coach class.
   */
  public static class TestCoach {

    @Test
    public void testOpp() {
      assertEquals(Coach.RED.opponent(), Coach.BLUE);
      assertEquals(Coach.BLUE.opponent(), Coach.RED);
    }

  }

  /**
   * Tests for the game play and flow of the model.
   */
  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class TestFullGameNoHoles {

    // test public beahvior
    private static ModelBase model = new ModelBase();
    private static Referee referee = new RefereeDefault();
    private static Coach red;
    private static Coach blue;
    private static GridCellReadOnly[][] state;
    private static Card left;
    // using alphabetical ordering to sequence


    @Test
    public void P1Start() {
      model.startGame(ConfigGrid.scannerToGrid(GRID_NO_HOLES),
                      ConfigCard.scannerToCardList(CC_SMALL), referee);
      // check coach red is actually a red coach
      red = model.curCoach();
      assertEquals(red, Coach.RED);
    }

    @Test
    public void P2FirstPlacedCard() {
      model.placeCard(0, 0, 0); // red plays  R _ _
      state = model.curGrid().readOnlyArray2D();
      left = state[0][0].getCard(); // this is actual pointer not a copy
      // left has av's of 1
      assertEquals(left.getCoach(), Coach.RED);
      // makes sure that if a card is placed with no battle, it stays same color

    }

    @Test
    public void P3SecondPlacedCard() {
      blue = model.curCoach();
      assertEquals(blue, Coach.BLUE);
      // make sure that we switch coaches properly
      model.placeCard(0, 0, 1); // blue plays so now board is ? B _
      state = model.curGrid().readOnlyArray2D();
      left = state[0][0].getCard(); // have to do again
      assertEquals(state[0][1].getCard().getCoach(), blue);
      // left vs mid = 3 < 9 so left loses
      assertEquals(left.getCoach(), blue); // WE FLIPPED THE CARD!


    }

    @Test
    public void P4LastPlacedCard() {
      model.placeCard(0, 0, 2); // red plays ? ? R
      state = model.curGrid().readOnlyArray2D();
      // right has av's of A

      // should beat mid and cascade!
      for (GridCellReadOnly c : state[0]) {
        assertTrue(c.hasCard());
        assertEquals(red, c.getCard().getCoach());
      }
    }

    @Test
    public void P5CheckWin() {
      assertTrue(model.curGrid().isFull());
      assertTrue(model.isGameOver());
      Assert.assertSame(model.winner(), red);
    }

  }

  /**
   * Tests for the Grid class.
   */
  public static class TestGrid {

    CellType[][] constructorArg;
    private Grid grid;

    @Before
    public void setUp() {
      constructorArg = new CellType[2][2];
      constructorArg[0][0] = CellType.CARD;
      constructorArg[0][1] = CellType.CARD;
      constructorArg[1][0] = CellType.CARD;
      constructorArg[1][1] = CellType.CARD;
      grid = new Grid(constructorArg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullGrid() {
      grid = new Grid(null);
    }

    @Test
    public void testGetNumHoles() {
      assertEquals(0, grid.getNumHoles());
    }

    @Test
    public void testReadOnlyGridArr() {
      GridCellReadOnly[][] readOnlyGrid = grid.readOnlyArray2D();
      assertEquals(constructorArg.length, readOnlyGrid.length);
      assertEquals(constructorArg[0].length, readOnlyGrid[0].length);
    }

    @Test
    public void testToString() {
      String expected = "2 2\nCC\nCC";
      assertEquals(expected, grid.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullGrid() {
      Grid grid = new Grid(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidGrid() {
      CellType[][] invalidGrid = new CellType[0][0];
      Grid grid = new Grid(invalidGrid);
    }


  }

  /**
   * Tests for the GridCellCard class.
   */
  public static class TestGridCellCard {

    private GridCellCard cell1;

    @Before
    public void setUp() {
      cell1 = new GridCellCard();
    }

    @Test
    public void testCanHaveCard() {
      assertTrue(cell1.canHaveCard());
    }

    @Test
    public void testHasCard() {
      Assert.assertFalse(cell1.hasCard());
    }

    @Test
    public void testGetCard() {
      Assert.assertNull(cell1.getCard());
    }


  }

  /**
   * Tests for the GridCellHole class.
   */
  public static class TestGridCellHole {

    private GridCellReadOnly cell1;

    @Before
    public void setUp() {
      cell1 = new GridCellHole();
    }

    @Test
    public void testCanHaveCard() {
      Assert.assertFalse(cell1.canHaveCard());
    }

    @Test
    public void testHasCard() {
      Assert.assertFalse(cell1.hasCard());
    }

    @Test(expected = IllegalStateException.class)
    public void testGetCard() {
      cell1.getCard();
    }

  }

}
