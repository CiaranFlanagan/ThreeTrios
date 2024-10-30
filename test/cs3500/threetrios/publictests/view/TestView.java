package cs3500.threetrios.publictests.view;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.utils.LineWriter;
import cs3500.threetrios.view.TextView;
import cs3500.threetrios.view.ThreeTriosView;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static cs3500.threetrios.controller.TestFiles.CC_SMALL;
import static cs3500.threetrios.controller.TestFiles.GRID_NO_HOLES;

public class TestView {
  private ThreeTriosView view;
  private ThreeTriosModel model;
  private final BattlePhaseReferee ref = new DefaultReferee();

  @Test
  public void test1() {
    model = new ThreeTriosModel();
    try {
      model.startGame(GridConfig.scannerToGrid(new Scanner(GRID_NO_HOLES)),
              CardConfig.scannerToCardList(new Scanner(CC_SMALL)), ref);
    } catch (FileNotFoundException x) {
      Assert.fail("file not found");
    }

    view = new TextView(model);
    view.render();
  }


  @Test
  public void test2() {
    model = new ThreeTriosModel();
    try {
      model.startGame(GridConfig.scannerToGrid(new Scanner(GRID_NO_HOLES)),
              CardConfig.scannerToCardList(new Scanner(CC_SMALL)), ref);
    } catch (FileNotFoundException ex) {
      Assert.fail("file not found");
    }
    view = new TextView(model);
    view.render();

   String cards = LineWriter.create().toString();
   CardConfig.scannerToCardList(new Scanner(cards));

  }

  // i want to test some model XXX
  public void test7(){
    Grid g = GridConfig.scannerToGrid(new Scanner("1 3\nXXX"));
    System.out.println(g);
    LineWriter lw =LineWriter.create().line("kc 1 2 3 4").endWith("ci 4 5 6 7");
    List<Card> loc = CardConfig.scannerToCardList(new Scanner(lw.toString()));
  }
}
