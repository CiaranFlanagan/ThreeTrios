package copy.view;


import copy.model.CoachColor;
import copy.model.ModelReadOnly;
import copy.view.utils.Utils;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * To represent a player in the game of three trios as a gui. This is also a listener of a
 * playable game and can propagate exceptions.
 */
public class GUIPlayerDelegate extends JFrame implements GameView {

  protected ModelReadOnly model;
  protected CoachColor coachColor;

  protected GUIHandBase viewRedHand;
  protected GUIHandBase viewBlueHand;
  protected GUIGridBase viewGrid;

  /**
   * Constructor.
   *
   * @param viewRedHand  the gui of the red hand
   * @param viewBlueHand the gui of the blue hand
   * @param viewGrid     the gui of the grid
   * @param coachColor        the color of the player this represents
   */
  public GUIPlayerDelegate(GUIHandBase viewRedHand,
                           GUIHandBase viewBlueHand,
                           GUIGridBase viewGrid,
                           CoachColor coachColor) {
    // field init
    this.coachColor = coachColor;
    this.viewRedHand = viewRedHand;
    this.viewBlueHand = viewBlueHand;
    this.viewGrid = viewGrid;

    // frame stuff
    setTitle(coachColor.toString());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 500);
    setVisible(true);

  }

  @Override
  public void paintComponents(Graphics g) {
    super.paintComponents(g);
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


  @Override
  public void renderMessage(String message) {
    Utils.popup(message, this);
  }

  @Override
  public void renderModel(ModelReadOnly model) {
    this.model = model;
    updateLayout();
    updateDelegateViews();
    repaint();
    setVisible(true);
    requestFocus();
  }

}
