package cs3500.threetrios.test.controller;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.model.Card;
import org.junit.Test;

import cs3500.threetrios.TestFiles;
import java.util.List;

public class TestCardConfig {
  @Test
  public void test1() {
    List<Card> cards = CardConfig.fileToTTCardList(TestFiles.cardConfig1);
    System.out.println(cards);
    List.of(1, 2, 3);
  }


}





