package cs3500.threetrios.view;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.ModelReadOnly;
import cs3500.threetrios.utils.extensions.ComponentHandler;
import cs3500.threetrios.utils.extensions.WasComponent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

/**
 * A graphical user interface (GUI) implementation of the View interface for the Three Trios game.
 * This class is responsible for visually rendering the game's current state in a windowed format.
 */
public class ViewGUI implements View<JFrame>  {
  private ModelReadOnly model;
  private JFrame frame;
  private HandGUI leftHand;
  private HandGUI rightHand;
  private GridGUI gridGUI;
  private Dimension dims;
  private static final Color VISIBLE_RED = new Color(246, 150, 151);
  private static final Color VISIBLE_BLUE = new Color(173, 216, 230);
  private static final Color VISIBLE_HOLE = Color.GRAY;
  private static final Color VISIBLE_EMPTY_CARD = new Color(144, 238, 144);

  public ViewGUI(ModelReadOnly model) {
    this.model = model;

    dims = new Dimension();
    dims.width = model.numCols() + 2;
    dims.height = model.numRows();

    leftHand = new HandGUI(Coach.RED);
    rightHand = new HandGUI(Coach.BLUE);
    gridGUI = new GridGUI(model.curGrid());
  }

  /**
   * Renders the GUI representation of the game.
   * This method will display the current game state in the GUI when implemented.
   */
  @Override
  public void renderTo(JFrame outputFrame) {
    this.frame = outputFrame;
    // Implementation to render the game state in a GUI will be added here.
    outputFrame.setVisible(true);
    outputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    outputFrame.setLayout(null);
    outputFrame.setSize(new Dimension(dims.width * cwidth(), dims.height * cheight()));

    outputFrame.add(leftHand);
    outputFrame.add(gridGUI);
    outputFrame.add(rightHand);

    outputFrame.addComponentListener(
            new ComponentHandler().handle(WasComponent.RESIZED, this::handleResize));
  }

  private void handleResize() {
    leftHand.handleResize();
    gridGUI.handleResize();
    rightHand.handleResize();
  }

  protected class HandGUI extends JPanel {
    private List<Card> hand;
    private Coach coach;

    HandGUI(Coach coach) {
      this.coach = coach;
      this.hand = model.curCoachesHands().get(coach);
      this.setVisible(true);
      this.setBackground(Color.YELLOW);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      int handCardHeight = dims.height * cheight() / hand.size();
      int[] y = new int[1];
      hand.forEach((c) -> renderCard(g, 0, y[0]++ * handCardHeight, c, handCardHeight));
    }

    private void handleResize() {
      int x = coach == Coach.RED ? 0 : (dims.width - 1) * cwidth();
      this.setBounds(x, 0, cwidth(),
                     cheight() * dims.height);
    }

  }

  protected class GridGUI extends JPanel {
    Grid grid;

    GridGUI(Grid grid) {
      this.grid = grid;
      this.setVisible(true);
      this.setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      GridCellReadOnly[][] readOnlyArray2D = grid.readOnlyArray2D();
      for(int row = 0; row < model.numRows(); row++) {
        for (int col = 0; col < model.numCols(); col++) {
          renderCell(g, col * cwidth(), row * cheight(), readOnlyArray2D[row][col]);
        }
      }
    }

    protected void renderCell(Graphics g, int x, int y, GridCellReadOnly cell) {
      g.setColor(cellToColor(cell));
      g.fillRect(x, y, cwidth(), cheight());
      if (cell.hasCard()) {
        renderCard(g, x, y, cell.getCard(), cheight());
      }
      g.setColor(Color.BLACK);
      g.drawRect(x, y, cwidth(), cheight());
    }

    public void handleResize() {
      this.setBounds(cwidth(), 0, cwidth() * (dims.width - 2),
                     cheight() * dims.height);
    }
  }

  protected void renderCard(Graphics g, int x, int y, Card card, int cardHeight) {
    String n = card.getAttackValue(CardinalDirection.NORTH).toString();
    String s = card.getAttackValue(CardinalDirection.SOUTH).toString();
    String e = card.getAttackValue(CardinalDirection.EAST).toString();
    String w = card.getAttackValue(CardinalDirection.WEST).toString();
    g.setColor(coachToColor(card.getCoach()));
    g.fillRect(x, y, cwidth(), cardHeight);
    g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
    g.setColor(Color.BLACK);
    g.drawRect(x, y, cwidth(), cardHeight);
    g.drawString(n, x + 7 * cwidth() / 16, y + cardHeight / 4);
    g.drawString(s, x + 7 * cwidth() / 16, y + 7 * cardHeight / 8);
    g.drawString(e, x + 3 * cwidth() / 4, y + cardHeight / 2);
    g.drawString(w, x + cwidth() / 8, y + cardHeight / 2);
  }

  protected Color coachToColor(Coach coach) {
    switch (coach)  {
      case RED:
        return VISIBLE_RED;
      case BLUE:
        return VISIBLE_BLUE;
      default:
        throw new IllegalArgumentException();
    }
  }

  protected Color cellToColor(GridCellReadOnly cell) {
    if (!cell.canHaveCard()) {
      return VISIBLE_HOLE;
    } else if (!cell.hasCard()) {
      return VISIBLE_EMPTY_CARD;
    }
    return Color.WHITE;
  }

  protected int cwidth() {
    return frame.getWidth() / dims.width;
  }

  protected int cheight() {
    return frame.getContentPane().getHeight() / dims.height;
  }

}
