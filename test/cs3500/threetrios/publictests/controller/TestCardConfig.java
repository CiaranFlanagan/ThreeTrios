package cs3500.threetrios.publictests.controller;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.model.Card;

import org.junit.Assert;
import org.junit.Test;

import cs3500.threetrios.TestFiles;
import cs3500.threetrios.utils.LineWriter;

import java.util.List;

/**
 * Tests for the CardConfig class.
 */
public class TestCardConfig {
  @Test
  public void test1() {
    List<Card> cards = CardConfig.scannerToCardList(TestFiles.CC_SMALL);
    System.out.println(cards);
    //<TTCard: bob 1 2 3 A>, <TTCard: kc A 4 7 9>, <TTCard: ciaran 1 2 3 4>
    String expected = LineWriter.create().endWith("[<TTCard: bob 1 2 3 A>, <TTCard: kc A 4 7 9>," +
            " <TTCard: ciaran 1 2 3 4>]").toString();
    Assert.assertEquals(expected, cards.toString());
  }


}





