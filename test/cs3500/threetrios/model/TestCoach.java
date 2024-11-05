package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Tests for package private and protected methods in the Coach class.
 */
public class TestCoach {
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

  @Test(expected = IllegalArgumentException.class)
  public void TestRemoveCardFromHandException() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach coachRed = new Coach(colorRed);
    Card card1 = new Card("TestCard1", new HashMap<>());
    Card card2 = new Card("TestCard2", new HashMap<>());
    Card card3 = new Card("TestCard3", new HashMap<>());
    coachRed.addCard(card1);
    coachRed.addCard(card2);
    coachRed.addCard(card3);
    Assert.assertEquals(coachRed.getHand().size(), 3);
    coachRed.removeCardFromHand(3);
  }

  @Test
  public void addCard() {
    Coach.Color colorRed = Coach.Color.Red;
    Coach coachRed = new Coach(colorRed);
    Card card1 = new Card("TestCard1", new HashMap<>());
    Card card2 = new Card("TestCard2", new HashMap<>());
    Card card3 = new Card("TestCard3", new HashMap<>());
    coachRed.addCard(card1);
    coachRed.addCard(card2);
    coachRed.addCard(card3);
    Assert.assertEquals(coachRed.getHand().size(), 3);
  }


}
