package cs3500.threetrios.test;

import cs3500.threetrios.model.CardConfig;
import cs3500.threetrios.model.IPlayer;
import cs3500.threetrios.model.done.ABoardCell;
import cs3500.threetrios.model.done.CardinalDirection;
import cs3500.threetrios.model.done.TTCard;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestCardConfig {
  @Test
  public void test1() {
    File f = new File("./src/carddb.txt");
    List<TTCard> cards = CardConfig.fileToTTCardList(f);
    System.out.println(cards);
  }


}





