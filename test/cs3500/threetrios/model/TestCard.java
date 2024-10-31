package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestCard {

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
  public void testGetCoach() {
    Coach coach = new Coach(Coach.Color.Red);
    Card card = new Card("TestCard", new HashMap<>());
    card.setCoach(coach);
    assertEquals(coach, card.getCoach());
  }
}
