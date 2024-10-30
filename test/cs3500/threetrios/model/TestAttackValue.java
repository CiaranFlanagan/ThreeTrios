package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

public class TestAttackValue {
  /**
   * P1_FromString_ValidInputs: Tests the fromString method with all valid inputs.
   */
  @Test
  public void P1_FromString_ValidInputs() {
    Assert.assertEquals("fromString(\"1\") should return ONE", AttackValue.ONE, AttackValue.fromString("1"));
    Assert.assertEquals("fromString(\"2\") should return TWO", AttackValue.TWO, AttackValue.fromString("2"));
    Assert.assertEquals("fromString(\"3\") should return THREE", AttackValue.THREE, AttackValue.fromString("3"));
    Assert.assertEquals("fromString(\"4\") should return FOUR", AttackValue.FOUR, AttackValue.fromString("4"));
    Assert.assertEquals("fromString(\"5\") should return FIVE", AttackValue.FIVE, AttackValue.fromString("5"));
    Assert.assertEquals("fromString(\"6\") should return SIX", AttackValue.SIX, AttackValue.fromString("6"));
    Assert.assertEquals("fromString(\"7\") should return SEVEN", AttackValue.SEVEN, AttackValue.fromString("7"));
    Assert.assertEquals("fromString(\"8\") should return EIGHT", AttackValue.EIGHT, AttackValue.fromString("8"));
    Assert.assertEquals("fromString(\"9\") should return NINE", AttackValue.NINE, AttackValue.fromString("9"));
    Assert.assertEquals("fromString(\"A\") should return A", AttackValue.A, AttackValue.fromString("A"));
  }
}
