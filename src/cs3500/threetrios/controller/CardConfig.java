package cs3500.threetrios.controller;

import cs3500.threetrios.model.AttackValue;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * To convert a file to a list of cards.
 */
public class CardConfig {

  /**
   * to convert a file to a list of cards.
   * @param file - the file to convert
   * @return a list of cards
   */
  public static List<Card> fileToTTCardList(File file) {
    if (file == null) {
      throw new IllegalArgumentException("null file");
    }
    FileReader fr;
    try {
      fr = new FileReader(file);
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("file not found: " + file.toPath());
    }
    Scanner sc = new Scanner(fr);
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