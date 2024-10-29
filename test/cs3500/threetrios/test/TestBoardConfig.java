package cs3500.threetrios.test;

import cs3500.threetrios.model.CardConfig;
import cs3500.threetrios.model.GridConfig;
import cs3500.threetrios.model.done.TTCard;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestBoardConfig {
  @Test
  public void test1() {
    File f = new File("./src/grid1.txt");
    System.out.println(GridConfig.fileToGridBoard(f));
  }
}
