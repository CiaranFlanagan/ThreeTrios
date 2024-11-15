package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.view.ViewGUI;
import org.junit.Before;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A test class for the graphical user interface (GUI) view of the Three Trios game, using a
 * JFrame for rendering. This class extends TestView and provides additional setup and methods
 * to visually display the game state for testing purposes.
 */
public class TestGuiView extends TestView<JFrame> {
  JFrame frame;

  /**
   * Sets up the test environment by initializing the JFrame used for rendering the GUI view.
   */
  @Before
  public void setup() {
    super.setup();
    SwingUtilities.invokeLater(() -> frame = new JFrame());
  }

  @Override
  public void testGridNoHolesView() {
    super.testGridNoHolesView();
    showGui();
  }

  @Override
  public void testGridConnectedView() {
    super.testGridConnectedView();
    showGui();
  }

  @Override
  public void testGridDisconnectedView() {
    super.testGridDisconnectedView();
    model.placeCard(0, 0, 0);
    showGui();
  }

  private void showGui() {
    view = new ViewGUI(model);
    SwingUtilities.invokeLater(() -> view.renderTo(frame));
    try {
      Thread.sleep(1000000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
