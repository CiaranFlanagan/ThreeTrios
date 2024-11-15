package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.view.ViewGUI;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestGuiView extends TestView<JFrame> {
  JFrame frame;

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
    showGui();
  }

  @Test
  public void testGridDisconnectedViewMidGame() {
    super.testGridDisconnectedView();
    model.placeCard(0, 0, 0);
    model.placeCard(0, 0, 1);
    model.placeCard(2, 0, 2);
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
