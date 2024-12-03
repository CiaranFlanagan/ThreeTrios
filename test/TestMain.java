import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * To test the entry point of this program. The philosophy is that we can give any
 * input and main doesn't error.
 */
public class TestMain {
  @Test
  public void testNoElements() {
    try {
      System.setOut(new PrintStream(new ByteArrayOutputStream()));
      Main.main(makeStringArr());
    } catch (Exception e) {
      Assert.fail();
      throw e;
    }
  }

  @Test
  public void testJunkElements() {
    try {
      System.setOut(new PrintStream(new ByteArrayOutputStream()));
      Main.main(makeStringArr("aah", "baaah", "ajfkjaklsdf"));
    } catch (Exception e) {
      Assert.fail();
      throw e;
    }
  }

  private String[] makeStringArr(String... args) {
    return args;
  }
}
