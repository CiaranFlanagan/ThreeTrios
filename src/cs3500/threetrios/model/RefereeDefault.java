package cs3500.threetrios.model;

/**
 * to represent the default referee or rules enforcer for the game of three trios.
 */
public class RefereeDefault implements IReferee {

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
      Coach ourCoach = ourCard.getCoach();
      GridCellAbstract them = us.getNeighborToThe(direction); // we know this isn't null
      Card theirCard;

      if (them.hasCard()) {
        theirCard = them.getCard();
      } else {
        continue;
      }
      Coach theirCoach = theirCard.getCoach();
      if (ourCoach != theirCoach && ourCard.beats(theirCard, direction)) {
        theirCard.setCoach(ourCoach);
        refereeBattlePhase(them);
      }
    }
  }

  @Override
  public void refereeBattlePhase(GridCellHole us) {
    throw new IllegalStateException("should not directly call a hole cell to battle");
  }
}




