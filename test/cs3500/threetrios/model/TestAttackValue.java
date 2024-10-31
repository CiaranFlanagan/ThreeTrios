package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the AttackValue enum.
 */
public class TestAttackValue {
  /**
   * P1_FromString_ValidInputs: Tests the fromString method with all valid inputs.
   */
  @Test
  public void TestFromString_ValidInputs() {
    Assert.assertEquals("fromString(\"1\") should return ONE",
            AttackValue.ONE, AttackValue.fromString("1"));
    Assert.assertEquals("fromString(\"2\") should return TWO",
            AttackValue.TWO, AttackValue.fromString("2"));
    Assert.assertEquals("fromString(\"3\") should return THREE",
            AttackValue.THREE, AttackValue.fromString("3"));
    Assert.assertEquals("fromString(\"4\") should return FOUR",
            AttackValue.FOUR, AttackValue.fromString("4"));
    Assert.assertEquals("fromString(\"5\") should return FIVE",
            AttackValue.FIVE, AttackValue.fromString("5"));
    Assert.assertEquals("fromString(\"6\") should return SIX",
            AttackValue.SIX, AttackValue.fromString("6"));
    Assert.assertEquals("fromString(\"7\") should return SEVEN",
            AttackValue.SEVEN, AttackValue.fromString("7"));
    Assert.assertEquals("fromString(\"8\") should return EIGHT",
            AttackValue.EIGHT, AttackValue.fromString("8"));
    Assert.assertEquals("fromString(\"9\") should return NINE",
            AttackValue.NINE, AttackValue.fromString("9"));
    Assert.assertEquals("fromString(\"A\") should return A",
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
    Assert.assertEquals("ONE toString should return \"1\"", "1", AttackValue.ONE.toString());
    Assert.assertEquals("TWO toString should return \"2\"", "2", AttackValue.TWO.toString());
    Assert.assertEquals("THREE toString should return \"3\"", "3", AttackValue.THREE.toString());
    Assert.assertEquals("FOUR toString should return \"4\"", "4", AttackValue.FOUR.toString());
    Assert.assertEquals("FIVE toString should return \"5\"", "5", AttackValue.FIVE.toString());
    Assert.assertEquals("SIX toString should return \"6\"", "6", AttackValue.SIX.toString());
    Assert.assertEquals("SEVEN toString should return \"7\"", "7", AttackValue.SEVEN.toString());
    Assert.assertEquals("EIGHT toString should return \"8\"", "8", AttackValue.EIGHT.toString());
    Assert.assertEquals("NINE toString should return \"9\"", "9", AttackValue.NINE.toString());
    Assert.assertEquals("A toString should return \"A\"", "A", AttackValue.A.toString());
  }
}
