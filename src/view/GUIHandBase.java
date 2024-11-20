package view;

import model.Card;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class GUIHandBase extends JPanel {

  protected static final Color VISIBLE_SELECTED = Color.GREEN;
  protected static final Color VISIBLE_HOVER = Color.WHITE;

  protected List<Card> hand;
  protected final DrawHand view;
  protected BufferedImage currentImage;

  public GUIHandBase(DrawHand view) {
    this.view = view;
  }

  public void updateHand(List<Card> hand) {
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
