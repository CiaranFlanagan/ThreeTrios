package cs3500.threetrios.controller;

import cs3500.threetrios.model.AttackValue;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Card;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * To convert a file to a list of cards.
 */
public class ConfigCard {

  /**
   * to take a scanner and create a list of cards in the order they came in the file.
   *
   * @param sc - the scanner
   * @return a list of cards
   */
  public static List<Card> scannerToCardList(Supplier<Scanner> scannerSupplier) {
    if (scannerSupplier == null) {
      throw new IllegalArgumentException("null supplier");
    }
    Scanner sc = scannerSupplier.get();
    return scannerToCardList(sc);
  }

  public static List<Card> scannerToCardList(Scanner sc) {
    if (sc == null) {
      throw new IllegalArgumentException("null scanner");
    }
    List<Card> cards = new ArrayList<>();
    try {
      while (sc.hasNext()) {
        String name = sc.next();
        Map<CardinalDirection, AttackValue> directionToAttackValues = new HashMap<>();
        directionToAttackValues.put(CardinalDirection.NORTH, AttackValue.fromString(sc.next()));
        directionToAttackValues.put(CardinalDirection.SOUTH, AttackValue.fromString(sc.next()));
        directionToAttackValues.put(CardinalDirection.EAST, AttackValue.fromString(sc.next()));
        directionToAttackValues.put(CardinalDirection.WEST, AttackValue.fromString(sc.next()));
        cards.add(new Card(name, directionToAttackValues));
      }
    } catch (NoSuchElementException ex) {
      throw new IllegalArgumentException("scanner error");
    }
    return cards;
  }
}