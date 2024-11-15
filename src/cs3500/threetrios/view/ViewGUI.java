package cs3500.threetrios.view;

import cs3500.threetrios.controller.HandleClickForGrid;
import cs3500.threetrios.controller.HandleClickForHand;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.ModelReadOnly;
import cs3500.threetrios.utils.IntPoint2D;
import cs3500.threetrios.utils.extensions.ComponentHandler;
import cs3500.threetrios.utils.extensions.WasComponent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;

/**
 * A graphical user interface (GUI) implementation of the View interface for the Three Trios game.
 * This class is responsible for visually rendering the game's current state in a windowed format.
 */
public class ViewGUI implements View<JFrame> {
  private static final Color VISIBLE_RED = new Color(246, 150, 151);
  private static final Color VISIBLE_BLUE = new Color(173, 216, 230);
  private static final Color VISIBLE_HOLE = Color.GRAY;
  private static final Color VISIBLE_EMPTY_CARD = new Color(144, 238, 144);
  private ModelReadOnly model;
  private JFrame frame;
  private HandGUI leftHand;
  private HandGUI rightHand;
  private GridGUI gridGUI;
  private Dimension dims;

  /**
   * A graphical user interface (GUI) implementation of the View interface for the Three Trios
   * game.
   * This class is responsible for visually rendering the game's current state in a windowed
   * format.
   * It displays the hands of both players and the game grid.
   */
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

    outputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    outputFrame.setLayout(null);
    outputFrame.setSize(new Dimension(500, 300));
    new HandleClickForHand(leftHand, model).init();
    new HandleClickForHand(rightHand, model).init();
    new HandleClickForGrid(gridGUI).init();

    outputFrame.add(leftHand);
    outputFrame.add(gridGUI);
    outputFrame.add(rightHand);

    outputFrame.addComponentListener(new ComponentHandler().handle(WasComponent.RESIZED,
                                                                   this :: handleResize));
    outputFrame.setVisible(true);
    outputFrame.repaint();
  }

  private void handleResize(ComponentEvent e) {
    leftHand.handleResize();
    gridGUI.handleResize();
    rightHand.handleResize();
  }

  protected void renderCard(Graphics g, int x, int y, Card card, int cardHeight) {
    String n = card.getAttackValue(CardinalDirection.NORTH).toString();
    String s = card.getAttackValue(CardinalDirection.SOUTH).toString();
    String e = card.getAttackValue(CardinalDirection.EAST).toString();
    String w = card.getAttackValue(CardinalDirection.WEST).toString();
    g.setColor(coachToColor(card.getCoach()));
    g.fillRect(x, y, gcwidth(), cardHeight);
    g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
    g.setColor(Color.BLACK);
    g.drawRect(x, y, gcwidth(), cardHeight);
    g.drawString(n, x + 7 * gcwidth() / 16, y + cardHeight / 4);
    g.drawString(s, x + 7 * gcwidth() / 16, y + 7 * cardHeight / 8);
    g.drawString(e, x + 3 * gcwidth() / 4, y + cardHeight / 2);
    g.drawString(w, x + gcwidth() / 8, y + cardHeight / 2);
  }

  protected Color coachToColor(Coach coach) {
    switch (coach) {
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

  /**
   *
   * @return width of a card in grid in frame's coordinate system
   */
  protected int gcwidth() {
    return frame.getWidth() / dims.width;
  }

  /**
   *
   * @return height of a card in grid in frame's coordinate system
   */
  protected int gcheight() {
    return frame.getContentPane().getHeight() / dims.height;
  }

  /**
   * Inner class representing the graphical display of a player's hand. Manages card selection and
   * highlights selected cards.
   */
  public class HandGUI extends JPanel {
    protected int xSelect;
    protected int ySelect;
    private List<Card> hand;
    private Coach coach;
    private Optional<IntPoint2D> selected;

    HandGUI(Coach coach) {
      this.coach = coach;
      this.hand = model.curCoachesHands().get(coach);
      this.selected = Optional.empty();
      this.setVisible(true);
      Color fillBlankSpaceColor = coach == Coach.RED ? VISIBLE_RED : VISIBLE_BLUE;
      this.setBackground(fillBlankSpaceColor);

    }

    public Coach getCoach() {
      return coach;
    }

    public Optional<IntPoint2D> getSelected() {
      return selected;
    }

    public void select(IntPoint2D modelCoordinates) {
      this.selected = Optional.of(modelCoordinates);
    }

    public void deselect() {
      this.selected = Optional.empty();
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      int[] y = new int[1];
      hand.forEach((c) -> renderCard(g, 0, y[0]++ *  cardHeight(), c,  cardHeight()));
      if (selected.isPresent()) {
        renderSelected(g, xSelect, ySelect,  cardHeight());
      }
    }

    protected int cardHeight() {
      return dims.height * gcheight() / hand.size();
    }

    private void handleResize() {
      int x = coach == Coach.RED ? 0 : (dims.width - 1) * gcwidth();
      this.setBounds(x, 0, gcwidth(), gcheight() * dims.height);
    }


    protected void renderSelected(Graphics g, int x, int y, int cardHeight) {
      g.setColor(Color.WHITE);
      int topCorner = selected.get().y * cardHeight;
      g.drawRect(0, topCorner, gcwidth(), cardHeight);
      g.drawRect(1, topCorner + 1, gcwidth() - 2, cardHeight - 2);
    }

    public int modelx(int x) {
      return x / gcwidth();
    }

    public int modely(int y) {
      return y / cardHeight();
    }

  }

  /**
   * Inner class representing the graphical display of the game grid, rendering cells and cards
   * based on the current game state.
   */
  public class GridGUI extends JPanel {
    protected Grid grid;

    GridGUI(Grid grid) {
      this.grid = grid;
      this.setVisible(true);
      this.setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      GridCellReadOnly[][] readOnlyArray2D = grid.readOnlyArray2D();
      for (int row = 0; row < model.numRows(); row++) {
        for (int col = 0; col < model.numCols(); col++) {
          renderCell(g, col * gcwidth(), row * gcheight(), readOnlyArray2D[row][col]);
        }
      }
    }

    protected void renderCell(Graphics g, int x, int y, GridCellReadOnly cell) {
      g.setColor(cellToColor(cell));
      g.fillRect(x, y, gcwidth(), gcheight());
      if (cell.hasCard()) {
        renderCard(g, x, y, cell.getCard(), gcheight());
      }
      g.setColor(Color.BLACK);
      g.drawRect(x, y, gcwidth(), gcheight());
    }

    protected void handleResize() {
      this.setBounds(gcwidth(), 0, gcwidth() * (dims.width - 2), gcheight() * dims.height);
    }

    public int modelx(int x) {
      return x / gcwidth();
    }

    public int modely(int y) {
      return y / gcheight();
    }

  }


  /**
   * to snap the input x-coordinate to the left-most coordinate of the card.
   *
   * @param x the x-coordinate on the panel
   * @return x coordinate on left of card
   */
  protected int snapToWidth(int x) {
    return (x / gcwidth()) * gcwidth();
  }

  /**
   * to snap the input y-coordinate to the leftmost coordinate of the card.
   *
   * @param y the y-coordinate on the panel.
   * @return y coordinate on left of card
   */
  protected int snapToHeight(int y, int cardHeight) {
    return (y / cardHeight) * cardHeight;
  }


}
