package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

public class TestReferee {

  @Test(expected = IllegalStateException.class)
  public void testRef1() {
    HoleCell holecard1 = new HoleCell();
    HoleCell holecard2 = new HoleCell();
    holecard1.link(holecard2, CardinalDirection.NORTH);
    new DefaultReferee().refereeBattle(holecard1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRef2() {
    HoleCell holecard1 = new HoleCell();
    CardCell cardcell1 = new CardCell();
    holecard1.link(cardcell1, CardinalDirection.NORTH);
    new DefaultReferee().refereeBattle(holecard1);
  }

  @Test
  public void testRef3() {
    HoleCell holecard1 = new HoleCell();
    CardCell cellcard1 = new CardCell();
    holecard1.link(cellcard1, CardinalDirection.NORTH);
    try {
      new DefaultReferee().refereeBattle(cellcard1);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      Assert.fail("should have no effect but should not throw");
    }

  }

  @Test
  public void testRef4() {
    CardCell cardCell1 = new CardCell();
    CardCell cardCell2 = new CardCell();
    Coach kc = new Coach("kc");
    Coach ciaran = new Coach("ciaran");
    Card card1 = Utils.makeCard("kc 1 2 3 4");
    card1.setCoach(kc); // this should change after battle
    Card card2 = Utils.makeCard("ciaran 5 6 7 8");
    card2.setCoach(ciaran);
    cardCell1.link(cardCell2, CardinalDirection.NORTH);
    cardCell1.placeCard(card1);
    cardCell2.placeCard(card2);
    new DefaultReferee().refereeBattle(cardCell2);
    Assert.assertSame(ciaran, card1.getCoach());


  }
}
