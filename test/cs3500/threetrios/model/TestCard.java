package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for protected and package private methods in the model.
 */
public class TestCard {

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
    Coach coach = new Coach(Coach.RED);
    card.setCoach(coach.getColor());
    assertEquals(coach.getColor(), card.getCoach());
  }

  @Test
  public void testSetAttackValueInDirection() {
    card.setAttackValueInDirection(AttackValue.ONE, CardinalDirection.NORTH);
    assertEquals(AttackValue.ONE, card.getAttackValue(CardinalDirection.NORTH));
  }

  @Test
  public void testSetCoach() {
    Coach coach = new Coach(Coach.RED);
    card.setCoach(coach.getColor());
    assertEquals(coach.getColor(), card.getCoach());
  }
}