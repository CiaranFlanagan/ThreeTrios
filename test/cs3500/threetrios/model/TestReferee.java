package cs3500.threetrios.model;

import cs3500.threetrios.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Referee class.
 */
public class TestReferee {
  private AGridCell holecard1;
  private AGridCell holecard2;
  private AGridCell cardCell1;
  private AGridCell cardCell2;
  private AGridCell cardCell3;
  private Coach kc;
  private Coach ciaran;
  private Coach bob;
  private Card card1;
  private Card card2;
  private Card card3;
  private DefaultReferee ref;

  @Before
  public void setup() {
    holecard1 = new HoleCell();
    holecard2 = new HoleCell();
    cardCell1 = new CardCell();
    cardCell2 = new CardCell();
    cardCell3 = new CardCell();
    kc = new Coach(Coach.Color.Red);
    ciaran = new Coach(Coach.Color.Blue);
    card1 = Utils.makeCard("c1 1 2 3 4");
    card2 = Utils.makeCard("c2 5 6 7 8");
    card3 = Utils.makeCard("c3 A A A A");
    ref = new DefaultReferee();
  }

  @Test(expected = IllegalStateException.class)
  public void testRef1() {
    HoleCell holecard1 = new HoleCell();
    HoleCell holecard2 = new HoleCell();
    holecard1.link(holecard2, CardinalDirection.NORTH);
    new DefaultReferee().refereeBattlePhase(holecard1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRef2() {
    HoleCell holecard1 = new HoleCell();
    CardCell cardcell1 = new CardCell();
    holecard1.link(cardcell1, CardinalDirection.NORTH);
    new DefaultReferee().refereeBattlePhase(holecard1);
  }

  @Test
  public void testRef3() {
    HoleCell holecard1 = new HoleCell();
    CardCell cellcard1 = new CardCell();
    holecard1.link(cellcard1, CardinalDirection.NORTH);
    try {
      new DefaultReferee().refereeBattlePhase(cellcard1);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      Assert.fail("should have no effect but should not throw");
    }

  }

  @Test
  public void testRef4() {
    card1.setCoach(kc); // this should change after battle
    card2.setCoach(ciaran);
    cardCell1.link(cardCell2, CardinalDirection.NORTH);
    cardCell1.placeCard(card1);
    cardCell2.placeCard(card2);
    ref.refereeBattlePhase(cardCell2);
    Assert.assertSame(ciaran, card2.getCoach());
    Assert.assertSame(ciaran, card1.getCoach());
  }

  @Test
  public void testRef5() {
    //
    //  C1
    //  |
    //  C2 - C3

    card1.setCoach(kc); // this should change after battle
    card2.setCoach(ciaran);
    card3.setCoach(kc);
    cardCell1.link(cardCell2, CardinalDirection.SOUTH);
    cardCell2.link(cardCell3, CardinalDirection.EAST);
    cardCell1.placeCard(card1);
    cardCell2.placeCard(card2);
    cardCell3.placeCard(card3);
    ref.refereeBattlePhase(cardCell3);
    Assert.assertSame(kc, card3.getCoach());
    Assert.assertSame(kc, card2.getCoach());
    Assert.assertSame(kc, card1.getCoach());
  }
}
