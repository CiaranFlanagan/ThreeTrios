package cs3500.threetrios.model;

/**
 * to represent the default referee or rules enforcer for the game of three trios.
 */
public class DefaultReferee implements BattlePhaseReferee {

  @Override
  public void refereeBattlePhase(AGridCell us) {
    us.acceptBattlePhase(this);
  }

  @Override
  public void refereeBattlePhase(CardCell us) {
    if (!doesCellHaveCard(us)) {
      return;
    }
    for (CardinalDirection direction : CardinalDirection.values()) {
      if (getCellNeighborToThe(direction, us) == null) {
        continue;
      }

      Card ourCard = getCellCard(us);
      Coach ourCoach = getCardCoach(ourCard);
      AGridCell them = getCellNeighborToThe(direction, us);
      Card theirCard;

      if (doesCellHaveCard(them)) {
        theirCard = getCellCard(them);
        System.out.println("we get here");
        System.out.println("us" + ourCard);
        System.out.println(direction);
        System.out.println("them" + theirCard);
      } else {
        continue;
      }
      Coach theirCoach = getCardCoach(theirCard);
      if (ourCoach != theirCoach && ourCard.beats(theirCard, direction)) {
        theirCard.setCoach(ourCoach);
        refereeBattlePhase(them);
      }
    }
  }

  @Override
  public void refereeBattlePhase(HoleCell us) {
    throw new IllegalStateException("should not be here");
  }
}




