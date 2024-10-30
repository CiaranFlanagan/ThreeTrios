package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the LineWriter class.
 */
public class TestLineWriter {
  private LineWriter lw;

  @Before
  public void setup() {
    this.lw = LineWriter.create();
  }

  @Test
  public void testWriteLine() {
    lw.line("i miss you");
    Assert.assertEquals(lw.toString(), "i miss you\n");
  }

  @Test
  public void testFullCase() {
    lw.line("dear ciaran");
    lw.line("hi");
    lw.endWith("sincerely kc");
    Assert.assertEquals(lw.toString(), "dear ciaran\nhi\nsincerely kc");
  }
}
