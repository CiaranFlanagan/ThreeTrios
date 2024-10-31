package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class TestGridLinks {
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
