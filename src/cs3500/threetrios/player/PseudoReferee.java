package cs3500.threetrios.player;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.GridCellCard;
import cs3500.threetrios.model.GridCellHole;
import cs3500.threetrios.model.Referee;

public class PseudoReferee implements Referee {
  private int[] count;

  public PseudoReferee(int[] count) {
    if (count == null) {
      throw new IllegalArgumentException();
    }
    this.count = count;
  }

  @Override
  public void refereeBattlePhase(GridCellAbstract us) {
    us.acceptBattlePhase(this);
  }

  @Override
  public void refereeBattlePhase(GridCellCard us) {
    if (!us.canHaveCard()) {
      return;
    }
    for (CardinalDirection direction : CardinalDirection.values()) {
      // if doesn't have neighbor, skip
      if (!us.hasNeighborToThe(direction)) {
        continue;
      }

      Card ourCard = us.getCard();
      Coach.Color ourCoach = ourCard.getCoachColor();
      GridCellAbstract them = us.getNeighborToThe(direction); // we know this isn't null
      Card theirCard;

      if (them.hasCard()) {
        theirCard = them.getCard();
      } else {
        continue;
      }

      Coach.Color theirCoach = theirCard.getCoachColor();
      System.out.println(ourCard);
      System.out.println(theirCard);
      System.out.println(ourCard.beats(theirCard, direction));
      if (ourCoach != theirCoach && ourCard.beats(theirCard, direction)) {
        //theirCard.setCoachColor(ourCoach);
        count[0] = count[0] + 1;
        refereeBattlePhase(them);
      }
    }
  }

  @Override
  public void refereeBattlePhase(GridCellHole us) {

  }

}
