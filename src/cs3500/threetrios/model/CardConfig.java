package cs3500.threetrios.model;

import cs3500.threetrios.model.done.AttackValue;
import cs3500.threetrios.model.done.CardinalDirection;
import cs3500.threetrios.model.done.TTCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CardConfig {

  public static List<TTCard> fileToTTCardList(File f) {
    if (f == null) {
      throw new IllegalArgumentException("null file");
    }
    FileReader fr;
    try {
      fr = new FileReader(f);
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("file not found: " + f.toPath());
    }
    Scanner sc = new Scanner(fr);
    List<TTCard> cards = new ArrayList<>();
    try {
      while (sc.hasNext()) {
        String name = sc.next();
        Map<CardinalDirection, AttackValue> directionToAttackValues = new HashMap<>();
        directionToAttackValues.put(CardinalDirection.NORTH, AttackValue.fromString(sc.next()));
        directionToAttackValues.put(CardinalDirection.SOUTH, AttackValue.fromString(sc.next()));
        directionToAttackValues.put(CardinalDirection.EAST, AttackValue.fromString(sc.next()));
        directionToAttackValues.put(CardinalDirection.WEST, AttackValue.fromString(sc.next()));
        cards.add(new TTCard(name, directionToAttackValues));
      }
    } catch (NoSuchElementException ex) {
      throw new IllegalArgumentException("scanner error");
    }
    return cards;
  }
}