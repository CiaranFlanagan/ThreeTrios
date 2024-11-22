package view;

import model.CoachColor;
import model.ModelReadOnly;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUIPlayerBase extends JFrame {

  protected ModelReadOnly model;
  protected CoachColor coach;

  protected GUIHandBase viewRedHand;
  protected GUIHandBase viewBlueHand;
  protected GUIGridBase viewGrid;

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
    updateDelegateControllers();
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

  private void updateDelegateControllers() {
    viewRedHand.updateHand(model.curCoachesHands().get(CoachColor.RED));
    viewBlueHand.updateHand(model.curCoachesHands().get(CoachColor.BLUE));
    viewGrid.updateGrid(model.curGrid());
  }

}
