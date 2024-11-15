package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.utils.LineWriter;
import cs3500.threetrios.view.ViewTextBase;

import org.junit.Assert;
import org.junit.Before;

import java.io.PrintStream;

/**
 * A test class for the text-based view of the Three Trios game. This class tests the rendering
 * of the game state to a text format for console output, ensuring the correct representation of
 * different grid configurations.
 */
public class TestTextView extends TestView<PrintStream> {
  Appendable log;

  @Before
  public void setup() {
    super.setup();
    log = new StringBuilder();
    view = new ViewTextBase(model, log);
  }

  @Override
  public void testGridNoHolesView() {
    super.testGridNoHolesView();
    view.renderTo(System.out);
    LineWriter lw = new LineWriter();
    Assert.assertEquals(lw.create().line("Player: RED").line("___").line("Hand:")
                                .line("bob 1 1 1 1").endWith("zeke A A A A").toString(),
                        log.toString());
  }


  @Override
  public void testGridConnectedView() {
    super.testGridConnectedView();
    view = new ViewTextBase(model, log);
    view.renderTo(System.out);
    LineWriter lw = new LineWriter();
    Assert.assertEquals(lw.create().line("Player: RED").line("_____").line("_   _").line("_____").
                                line("_   _").line("_____").line("Hand:").line("Card1 1 1 1 1")
                                .line("Card3 3 3 3 3")
                                .line("Card5 5 5 5 5").line("Card7 7 7 7 7").line("Card9 9 9 9 9")
                                .line("Card11 1 1 1 1").line("Card13 3 3 3 3")
                                .line("Card15 5 5 5 5")
                                .line("Card17 7 7 7 7").endWith("Card19 9 9 9 9").toString(),
                        log.toString());
  }

  @Override
  public void testGridDisconnectedView() {
    super.testGridDisconnectedView();
    view = new ViewTextBase(model, log);
    view.renderTo(System.out);
    LineWriter lw = new LineWriter();
    Assert.assertEquals(lw.create().line("Player: RED").line("___").line("   ").line("___").
                                line("_  ").line("Hand:").line("Card1 1 1 1 1")
                                .line("Card3 3 3 3 3")
                                .line("Card5 5 5 5 5").line("Card7 7 7 7 7").line("Card9 9 9 9 9")
                                .line("Card11 1 1 1 1").line("Card13 3 3 3 3")
                                .line("Card15 5 5 5 5")
                                .line("Card17 7 7 7 7").endWith("Card19 9 9 9 9").toString(),
                        log.toString());
  }


}
