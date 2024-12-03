package copy.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * To represent a view only gui representation of a hand in Three Trios.
 */
public class GUIHandBase extends JPanel {

  protected static final Color VISIBLE_SELECTED = Color.GREEN;
  protected static final Color VISIBLE_HOVER = Color.WHITE;
  protected final DrawHand view;
  protected List<CardImpl> hand;
  protected BufferedImage currentImage;

  /**
   * Constructor.
   * @param handView the artist of the hand
   */
  public GUIHandBase(DrawHand handView) {
    this.view = handView;
  }

  /**
   * To update the hand that this gui represents.
   *
   * @param hand the new hand
   */
  public void updateHand(List<CardImpl> hand) {
    this.hand = hand;
    this.setVisible(true);
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    currentImage = new BufferedImage(getWidth(), getHeight(), 1);
    view.render(hand, currentImage);
    g.drawImage(currentImage, 0, 0, null);
  }

}
