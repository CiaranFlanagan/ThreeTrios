package cs3500.threetrios.model;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.GridCellCard;
import cs3500.threetrios.model.GridCellHole;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Referee class.
 */
public class TestReferee {

  private GridCellHole holeCell1;
  private GridCellHole holeCell2;
  private GridCellAbstract cardCell1;
  private GridCellAbstract cardCell2;
  private GridCellAbstract cardCell3;
  private Coach kc;
  private Coach ciaran;
  private Card card1;
  private Card card2;
  private Card card3;
  private RefereeDefault ref;

  @Before
  public void setup() {
    holeCell1 = new GridCellHole();
    holeCell2 = new GridCellHole();
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

    holeCell1.link(holeCell1, CardinalDirection.NORTH);
   ref.refereeBattlePhase(holeCell1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRef2() {
    holeCell2.link(cardCell1, CardinalDirection.NORTH);
    ref.refereeBattlePhase(holeCell2);
  }

  @Test
  public void testRef3() {
    holeCell2.link(cardCell2, CardinalDirection.EAST);
    cardCell2.placeCard(card1);
    card1.setCoach(kc);
    ref.refereeBattlePhase(cardCell2);
    Assert.assertEquals(holeCell2.canHaveCard(), false);
    Assert.assertEquals(cardCell2.getCard().getCoach(), kc);
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
