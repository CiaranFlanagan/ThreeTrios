package view;

import model.CoachColor;
import model.ModelReadOnly;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * To represent a base view of a player in the game of three trios.
 */
public class GUIPlayerBase extends JFrame {

  protected ModelReadOnly model;
  protected CoachColor coach;

  protected GUIHandBase viewRedHand;
  protected GUIHandBase viewBlueHand;
  protected GUIGridBase viewGrid;

  /**
   * Constructor.
   * @param viewRedHand the gui of the red hand
   * @param viewBlueHand the gui of the blue hand
   * @param viewGrid the gui of the grid
   * @param coachColor the color of the coach of the player this represents
   */
  public GUIPlayerBase(GUIHandBase viewRedHand,
                       GUIHandBase viewBlueHand,
                       GUIGridBase viewGrid,
                       CoachColor coachColor) {

    this.coach = coachColor;
    this.viewRedHand = viewRedHand;
    this.viewBlueHand = viewBlueHand;
    this.viewGrid = viewGrid;

    // frame stuff
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 500);
  }

  // assumes model got set
  protected void updateModel(ModelReadOnly model) {
    this.model = model;
    updateLayout();
    updateDelegateViews();
    repaint();
    setVisible(true);
  }

  private void updateLayout() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1;
    this.setLayout(new GridBagLayout());
    gbc.gridx = 0;
    gbc.weightx = 1;
    this.add(viewRedHand, gbc);
    gbc.gridx = 1;
    gbc.weightx = model.numCols();
    this.add(viewGrid, gbc);
    gbc.gridx = 2;
    gbc.weightx = 1;
    this.add(viewBlueHand, gbc);
  }

  private void updateDelegateViews() {
    viewRedHand.updateHand(model.curCoachesHands().get(CoachColor.RED));
    viewBlueHand.updateHand(model.curCoachesHands().get(CoachColor.BLUE));
    viewGrid.updateGrid(model.curGrid());
  }

}
