package cs3500.threetrios.model;

/**
 * to represent the default referee or rules enforcer for the game of three trios.
 */
public final class RefereeDefault implements Referee {

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
        theirCard.setCoachColor(ourCoach);
        System.out.println("aah");
        refereeBattlePhase(them);
      }
    }
  }

  @Override
  public void refereeBattlePhase(GridCellHole us) {
    throw new IllegalStateException("should not directly call a hole cell to battle");
  }
}




