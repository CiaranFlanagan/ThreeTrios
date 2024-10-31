package cs3500.threetrios.test.model.unit;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import cs3500.threetrios.model.AttackValue;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;

import static org.junit.Assert.*;

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
  public void testGetAttackValues() {
    assertEquals(attackValues, card.getAttackValues());
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
    String expected = "<TTCard: TestCard 5 3 4 2>";
    assertEquals(expected, card.toString());
  }
}