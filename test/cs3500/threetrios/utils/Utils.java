package cs3500.threetrios.utils;

import cs3500.threetrios.model.AttackValue;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Utility class for testing.
 */
public class Utils {
  /**
   * Makes a card from a string.
   *
   * @param nameAndAttackValuesWithSpaces the string to parse
   * @return the card
   */
  public static Card makeCard(String nameAndAttackValuesWithSpaces) {
    Scanner sc = new Scanner(nameAndAttackValuesWithSpaces);
    Map<CardinalDirection, AttackValue> map = new EnumMap<>(CardinalDirection.class);
    String name = sc.next();
    map.put(CardinalDirection.NORTH, AttackValue.fromString(sc.next()));
    map.put(CardinalDirection.SOUTH, AttackValue.fromString(sc.next()));
    map.put(CardinalDirection.EAST, AttackValue.fromString(sc.next()));
    map.put(CardinalDirection.WEST, AttackValue.fromString(sc.next()));
    return new Card(name, map);
  }

  public static Scanner safeFileToScanner(File f) {
    try {
      return new Scanner(f);
    } catch (FileNotFoundException ex) {
      throw new RuntimeException("file not found, check TestFiles");
    }
  }

  @Test
  public void testMakeCard() {
    Assert.assertEquals(makeCard("kc 1 2 3 4").toString(),
            "<TTCard: kc 1 2 3 4>");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeBadCard() {
    makeCard("kc 1 2 3 Z");
  }


}
