package test;

import utils.ConfigCard;
import utils.ConfigGrid;
import model.Model;
import model.ModelBase;
import model.Referee;
import model.RefereeDefault;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.LineWriter;
import view.View;
import view.ViewTextBase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static utils.TestFiles.CC_LARGE;
import static utils.TestFiles.CC_SMALL;
import static utils.TestFiles.GRID_CONNECTED_HOLES;
import static utils.TestFiles.GRID_DISC_HOLES;
import static utils.TestFiles.GRID_NO_HOLES;

/**
 * A test class for the text-based view of the Three Trios game. This class tests the rendering
 * of the game state to a text format for console output, ensuring the correct representation of
 * different grid configurations.
 */
public class TestTextView {

  private final Referee ref = new RefereeDefault();
  protected View<PrintStream> view;
  protected Model model;
  protected Appendable log;
  private final OutputStream internalOut = new ByteArrayOutputStream();
  private final PrintStream out = new PrintStream(internalOut);

  @Before
  public void setup() {
    model = new ModelBase();
    log = new StringBuilder();
    view = new ViewTextBase(model, log);
  }

  @Test
  public void testGridNoHolesView() {
    model.startGame(ConfigGrid.scannerToGrid(GRID_NO_HOLES),
                    ConfigCard.scannerToCardList(CC_SMALL),
                    ref);
    LineWriter lw = new LineWriter();
    view.renderTo(out);
    System.out.println(internalOut.toString());
    Assert.assertEquals(lw.create()
                          .line("Player: RED")
                          .line("___")
                          .line("Hand:")
                          .line("bob 1 1 1 1")
                          .endWith("zeke A A A A")
                          .toString(), log.toString());
  }

  @Test
  public void testGridConnectedView() {
    model.startGame(ConfigGrid.scannerToGrid(GRID_CONNECTED_HOLES),
                    ConfigCard.scannerToCardList(CC_LARGE),
                    ref);
    LineWriter lw = new LineWriter();
    view.renderTo(out);
    Assert.assertEquals(lw.create()
                          .line("Player: RED")
                          .line("_____")
                          .line("_   _")
                          .line("_____")
                          .line("_   _")
                          .line("_____")
                          .line("Hand:")
                          .line("Card1 1 1 1 1")
                          .line("Card3 3 3 3 3")
                          .line("Card5 5 5 5 5")
                          .line("Card7 7 7 7 7")
                          .line("Card9 9 9 9 9")
                          .line("Card11 1 1 1 1")
                          .line("Card13 3 3 3 3")
                          .line("Card15 5 5 5 5")
                          .line("Card17 7 7 7 7")
                          .endWith("Card19 9 9 9 9")
                          .toString(), log.toString());
  }

  @Test
  public void testGridDisconnectedView() {
    model.startGame(ConfigGrid.scannerToGrid(GRID_DISC_HOLES),
                    ConfigCard.scannerToCardList(CC_LARGE),
                    ref);
    LineWriter lw = new LineWriter();
    view.renderTo(out);
    Assert.assertEquals(lw.line("Player: RED")
                          .line("___")
                          .line("   ")
                          .line("___")
                          .line("_  ")
                          .line("Hand:")
                          .line("Card1 1 1 1 1")
                          .line("Card3 3 3 3 3")
                          .line("Card5 5 5 5 5")
                          .endWith("Card7 7 7 7 7")
                          .toString(), log.toString());
  }


}
