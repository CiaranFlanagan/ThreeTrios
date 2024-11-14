package cs3500.threetrios.view;
import cs3500.threetrios.model.ModelReadOnly;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;
import java.awt.Graphics;

/**
 * A graphical user interface (GUI) implementation of the View interface for the Three Trios game.
 * This class is responsible for visually rendering the game's current state in a windowed format.
 */
public class ViewGUI extends JPanel implements View<JFrame>  {
  private ModelReadOnly model;

  ViewGUI(ModelReadOnly model) {
    this.model = model;
  }

  /**
   * Renders the GUI representation of the game.
   * This method will display the current game state in the GUI when implemented.
   */
  @Override
  public void render(JFrame outputFrame) {
    // Implementation to render the game state in a GUI will be added here.
    outputFrame.setVisible(true);
    outputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    outputFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
    outputFrame.add();
  }

  private class HandGUI extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
    }
  }

  private class GridGUI extends JPanel {

  }

}
