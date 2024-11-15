

package cs3500.threetrios.test.model.sequence;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.ModelBase;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static cs3500.threetrios.controller.TestFiles.CC_SMALL;
import static cs3500.threetrios.controller.TestFiles.GRID_NO_HOLES;


/**
 * Tests for the game play and flow of the model.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFullGameNoHoles {
  // test public beahvior
  private static ModelBase model = new ModelBase();
  private static Referee referee = new RefereeDefault();
  private static Coach red;
  private static Coach blue;
  private static GridCellReadOnly[][] state;
  private static Card left;
  // using alphabetical ordering to sequence


  @Test
  public void P1Start() {
    model.startGame(ConfigGrid.scannerToGrid(GRID_NO_HOLES),
            ConfigCard.scannerToCardList(CC_SMALL), referee);
    // check coach red is actually a red coach
    red = model.curCoach();
    Assert.assertEquals(red, Coach.RED);
  }

  @Test
  public void P2FirstPlacedCard() {
    model.placeCard(0, 0, 0); // red plays  R _ _
    state = model.curGrid().readOnlyArray2D();
    left = state[0][0].getCard(); // this is actual pointer not a copy
    // left has av's of 1
    Assert.assertEquals(left.getCoach(), Coach.RED);
    // makes sure that if a card is placed with no battle, it stays same color

  }

  @Test
  public void P3SecondPlacedCard() {
    blue = model.curCoach();
    Assert.assertEquals(blue, Coach.BLUE);
    // make sure that we switch coaches properly
    model.placeCard(0, 0, 1); // blue plays so now board is ? B _
    state = model.curGrid().readOnlyArray2D();
    left = state[0][0].getCard(); // have to do again
    Assert.assertEquals(state[0][1].getCard().getCoach(), blue);
    // left vs mid = 3 < 9 so left loses
    Assert.assertEquals(left.getCoach(), blue); // WE FLIPPED THE CARD!


  }

  @Test
  public void P4LastPlacedCard() {
    model.placeCard(0, 0, 2); // red plays ? ? R
    state = model.curGrid().readOnlyArray2D();
    // right has av's of A

    // should beat mid and cascade!
    for (GridCellReadOnly c : state[0]) {
      Assert.assertTrue(c.hasCard());
      Assert.assertEquals(red, c.getCard().getCoach());
    }
  }

  @Test
  public void P5CheckWin() {
    Assert.assertTrue(model.curGrid().isFull());
    Assert.assertTrue(model.isGameOver());
    Assert.assertSame(model.winner(), red);
  }
}
