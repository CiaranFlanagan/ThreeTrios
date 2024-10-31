package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.IReferee;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.utils.LineWriter;
import cs3500.threetrios.view.ViewTextBase;
import cs3500.threetrios.view.View;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static cs3500.threetrios.controller.TestFiles.CC_SMALL;
import static cs3500.threetrios.controller.TestFiles.GRID_NO_HOLES;

/**
 * Tests for the view.
 */
public class TestView {
  private View view;
  private ModelBase model;
  private final IReferee ref = new RefereeDefault();

  @Test
  public void test1() {
    model = new ModelBase();
    try {
      model.startGame(ConfigGrid.scannerToGrid(new Scanner(GRID_NO_HOLES)),
              ConfigCard.scannerToCardList(new Scanner(CC_SMALL)), ref);
    } catch (FileNotFoundException x) {
      Assert.fail("file not found");
    }

    view = new ViewTextBase(model);
    view.render();
  }


  @Test
  public void test2() {
    model = new ModelBase();
    try {
      model.startGame(ConfigGrid.scannerToGrid(new Scanner(GRID_NO_HOLES)),
              ConfigCard.scannerToCardList(new Scanner(CC_SMALL)), ref);
    } catch (FileNotFoundException ex) {
      Assert.fail("file not found");
    }
    view = new ViewTextBase(model);
    view.render();

    String cards = LineWriter.create().toString();
    ConfigCard.scannerToCardList(new Scanner(cards));
  }

  // i want to test some model XXX
  public void test7() {
    Grid g = ConfigGrid.scannerToGrid(new Scanner("1 3\nXXX"));
    System.out.println(g);
    LineWriter lw = LineWriter.create().line("kc 1 2 3 4").endWith("ci 4 5 6 7");
    List<Card> loc = ConfigCard.scannerToCardList(new Scanner(lw.toString()));
  }
}
