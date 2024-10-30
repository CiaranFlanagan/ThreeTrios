package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {
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

  @Test
  public void testMakeCard() {
    Assert.assertEquals(makeCard("kc 1 2 3 4").toString(), "<TTCard: kc 1 2 3 4>");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeBadCard() {
   makeCard("kc 1 2 3 Z");
  }
}
