package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestCoach {

  @Before
  public void setUp() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach.Color colorBlue = Coach.Color.Blue;
    HashMap<CardinalDirection, AttackValue> attackValues = new HashMap<>();
    attackValues.put(CardinalDirection.NORTH, AttackValue.FIVE);
    attackValues.put(CardinalDirection.SOUTH, AttackValue.THREE);
    attackValues.put(CardinalDirection.EAST, AttackValue.FOUR);
    attackValues.put(CardinalDirection.WEST, AttackValue.TWO);
    Card card = new Card("TestCard", attackValues);
  }

  @Test
  public void TestColorToString() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach.Color colorBlue = Coach.Color.Blue;
    Assert.assertEquals(colorRed.toString(), "Red");
    Assert.assertEquals(colorBlue.toString(), "Blue");
  }


  @Test
  public void TestCoachConstructor() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach.Color colorBlue = Coach.Color.Blue;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
  }

  @Test
  public void TestGetColor() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach.Color colorBlue = Coach.Color.Blue;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
    Assert.assertEquals(coachRed.getColor(), colorRed);
    Assert.assertEquals(coachBlue.getColor(), colorBlue);
  }

  @Test
  public void TestGetHand() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach.Color colorBlue = Coach.Color.Blue;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
    Assert.assertEquals(coachRed.getHand(), new ArrayList<Card>());
    Assert.assertEquals(coachBlue.getHand(), new ArrayList<Card>());
  }

  @Test
  public void TestRemoveCardFromHand() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach.Color colorBlue = Coach.Color.Blue;
    Coach coachRed = new Coach(colorRed);
    Coach coachBlue = new Coach(colorBlue);
    Card card1 = new Card("TestCard1", new HashMap<>());
    Card card2 = new Card("TestCard2", new HashMap<>());
    Card card3 = new Card("TestCard3", new HashMap<>());
    coachRed.addCard(card1);
    coachRed.addCard(card2);
    coachRed.addCard(card3);
    Assert.assertEquals(coachRed.getHand().size(), 3);
    coachRed.removeCardFromHand(1);
    Assert.assertEquals(coachRed.getHand().size(), 2);
  }
}
