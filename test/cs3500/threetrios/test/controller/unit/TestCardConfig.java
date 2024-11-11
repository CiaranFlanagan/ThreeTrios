package cs3500.threetrios.test.controller.unit;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.model.Card;

import org.junit.Assert;
import org.junit.Test;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.utils.LineWriter;

import java.util.List;

/**
 * Tests for the CardConfig class.
 */
public class TestCardConfig {
  @Test
  public void test1() {
    List<Card> cards = ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(TestFiles.
            CC_SMALL));
    System.out.println(cards);
    //<TTCard: bob 1 2 3 A>, <TTCard: kc A 4 7 9>, <TTCard: ciaran 1 2 3 4>
    String expected = LineWriter.create().endWith("[bob 1 1 1 1, " +
            "kc 5 5 5 5, " +
            "zeke A A A A, " +
            "ciaran 1 1 1 1]").toString();
    Assert.assertEquals(expected, cards.toString());
  }


}





