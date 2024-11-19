package utils;

import model.AttackValue;
import model.Card;
import model.CardinalDirection;
import model.GridCellReadOnly;
import model.Model;
import model.ModelReadOnly;
import org.junit.Assert;
import org.junit.Test;
import view.View;
import view.ViewGUI;

import javax.swing.JFrame;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Utility class for testing.
 */
public class Utils {

  /**
   * Retrieves the card at a specific position in the grid if a card is present.
   *
   * @param model the Model containing the grid
   * @param row the row index of the desired position
   * @param col the column index of the desired position
   * @return an Optional containing the Card at the specified position, or empty if no card exists
   */
  public static Optional<Card> cardAt(Model model, int row, int col) {
    GridCellReadOnly cell = model.curGrid().readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard());
    } else {
      return Optional.empty();
    }
  }

  public static void inspect(ModelReadOnly model) {
    try {
      View<JFrame> view = new ViewGUI(model);
      JFrame frame = new JFrame("three trios hwk 6");
      view.renderTo(frame);
      Thread.sleep(1000000);
    } catch (InterruptedException e) {
      System.err.println("interrupted");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeBadCard() {
    makeCard("kc 1 2 3 Z");
  }

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

  @Test
  public void testMakeCard() {
    Assert.assertEquals(makeCard("kc 1 2 3 4").toString(), "kc 1 2 3 4");
  }


}
