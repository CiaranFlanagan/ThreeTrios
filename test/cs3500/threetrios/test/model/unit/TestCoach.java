package cs3500.threetrios.test.model.unit;

import cs3500.threetrios.model.Coach;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.threetrios.model.AttackValue;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;

/**
 * Tests for the Coach class.
 */
public class TestCoach {

  @Before
  public void setUp() {
    Coach colorRed = Coach.RED;
    Coach colorBlue = Coach.BLUE;
    HashMap<CardinalDirection, AttackValue> attackValues = new HashMap<>();
    attackValues.put(CardinalDirection.NORTH, AttackValue.FIVE);
    attackValues.put(CardinalDirection.SOUTH, AttackValue.THREE);
    attackValues.put(CardinalDirection.EAST, AttackValue.FOUR);
    attackValues.put(CardinalDirection.WEST, AttackValue.TWO);
    Card card = new Card("TestCard", attackValues);
  }

  @Test
  public void TestColorToString() {
    Coach colorRed = Coach.RED;
    Coach colorBlue = Coach.BLUE;
    Assert.assertEquals(colorRed.toString(), "Red");
    Assert.assertEquals(colorBlue.toString(), "Blue");
  }


  @Test
  public void TestCoachConstructor() {
    Coach colorRed = Coach.RED;
    Coach colorBlue = Coach.BLUE;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
    Assert.assertEquals(coachRed.getColor(), colorRed);
    Assert.assertEquals(coachBlue.getColor(), colorBlue);
  }

  @Test
  public void TestGetColor() {
    Coach colorRed = Coach.RED;
    Coach colorBlue = Coach.BLUE;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
    Assert.assertEquals(coachRed.getColor(), colorRed);
    Assert.assertEquals(coachBlue.getColor(), colorBlue);
  }

  @Test
  public void TestGetHand() {
    Coach colorRed = Coach.RED;
    Coach colorBlue = Coach.BLUE;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
    Assert.assertEquals(coachRed.getHand(), new ArrayList<Card>());
    Assert.assertEquals(coachBlue.getHand(), new ArrayList<Card>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestCoachConstructorNull() {
    Coach coach = new Coach(null);
  }

}
