package cs3500.threetrios.model;

import cs3500.threetrios.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Referee class.
 */
public class TestReferee {
  private GridCellAbstract cardCell1;
  private GridCellAbstract cardCell2;
  private GridCellAbstract cardCell3;
  private Coach kc;
  private Coach ciaran;
  private Coach bob;
  private Card card1;
  private Card card2;
  private Card card3;
  private RefereeDefault ref;

  @Before
  public void setup() {
    GridCellAbstract holecard1 = new GridCellHole();
    GridCellAbstract holecard2 = new GridCellHole();
    cardCell1 = new GridCellCard();
    cardCell2 = new GridCellCard();
    cardCell3 = new GridCellCard();
    kc = new Coach(Coach.Color.Red);
    ciaran = new Coach(Coach.Color.Blue);
    card1 = Utils.makeCard("c1 1 2 3 4");
    card2 = Utils.makeCard("c2 5 6 7 8");
    card3 = Utils.makeCard("c3 A A A A");
    ref = new RefereeDefault();
  }

  @Test(expected = IllegalStateException.class)
  public void testRef1() {
    GridCellHole holecard1 = new GridCellHole();
    GridCellHole holecard2 = new GridCellHole();
    holecard1.link(holecard2, CardinalDirection.NORTH);
    new RefereeDefault().refereeBattlePhase(holecard1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRef2() {
    GridCellHole holecard1 = new GridCellHole();
    GridCellCard cardcell1 = new GridCellCard();
    holecard1.link(cardcell1, CardinalDirection.NORTH);
    new RefereeDefault().refereeBattlePhase(holecard1);
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
