package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.utils.LineWriter;
import cs3500.threetrios.view.ViewTextBase;
import cs3500.threetrios.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static cs3500.threetrios.controller.TestFiles.CC_SMALL;
import static cs3500.threetrios.controller.TestFiles.GRID_CONNECTED_HOLES;
import static cs3500.threetrios.controller.TestFiles.GRID_DISC_HOLES;
import static cs3500.threetrios.controller.TestFiles.GRID_NO_HOLES;

/**
 * Tests for the view.
 */
public class TestView {
  private View view;
  private ModelBase model;

  private StringBuilder log;
  private final Referee ref = new RefereeDefault();

  @Before
  public void setUp() {
    model = new ModelBase();
    log = new StringBuilder();
  }

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

  public void test7() {
    Grid g = ConfigGrid.scannerToGrid(new Scanner("1 3\nXXX"));
    System.out.println(g);
    LineWriter lw = LineWriter.create().line("kc 1 2 3 4").endWith("ci 4 5 6 7");
    List<Card> loc = ConfigCard.scannerToCardList(new Scanner(lw.toString()));
  }

  @Test
  public void testGridNoHolesView() {
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_NO_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_SMALL)), ref);
    view = new ViewTextBase(model, log);
    view.render();
    LineWriter lw = new LineWriter();
    Assert.assertEquals(lw.create().line("Player: RED").line("___").line("Hand:")
            .line("bob 1 1 1 1").endWith("zeke A A A A").toString(), log.toString());
  }

  //THIS SHOULD THROW AN EXCEPTION: NOT ENOUGH CARDS TO SART THE GAME
  @Test
  public void testGridConnectedView() {
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_CONNECTED_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_SMALL)), ref);
    view = new ViewTextBase(model, log);
    view.render();
    LineWriter lw = new LineWriter();
    Assert.assertEquals(lw.create().line("Player: RED").line("_____").line("_   _").line("_____").
            line("_   _").line("_____").line("Hand:").line("bob 1 1 1 1")
            .endWith("zeke A A A A").toString(), log.toString());
  }

  //THIS SHOULD THROW AN EXCEPTION: NOT ENOUGH CARDS TO START THE GAME
  @Test
  public void testGridDisconnectedView() {
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_DISC_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_SMALL)), ref);
    view = new ViewTextBase(model, log);
    view.render();
    LineWriter lw = new LineWriter();
    Assert.assertEquals(lw.create().line("Player: RED").line("__ ").line(" __").line("__ ").
            line(" __").line("Hand:").line("bob 1 1 1 1")
            .endWith("zeke A A A A").toString(), log.toString());
  }

}
