package cs3500.threetrios.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCard {
  @Test
  public void testGetCoach() {
    Coach coach = new Coach(Coach.Color.Red);
    Card card = null;
    card.setCoach(coach);
    assertEquals(coach, card.getCoach());
  }
}
