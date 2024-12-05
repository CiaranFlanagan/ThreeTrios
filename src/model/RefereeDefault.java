package model;

/**
 * To represent the default referee or rules enforcer for the game of three trios.
 */
public final class RefereeDefault implements Referee {

  /**
   * Takes in a visitable grid cell (by the referee) and accepts a battle phase, which evaluates
   * the given grid cell based on game rules.
   *
   * @param us - a board cell
   */
  @Override
  public void refereeBattlePhase(GridCellVisitable us) {
    us.acceptBattlePhase(this);
  }

  /**
   * Determines the outcome of a battle phase for a card cell.
   * @param us - the card cell currently being evaluated
   */
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
      CoachColor ourCoach = ourCard.getCoach();
      GridCellAbstract them = us.getNeighborToThe(direction); // we know this isn't null
      Card theirCard;

      if (them.hasCard()) {
        theirCard = them.getCard();
      } else {
        continue;
      }

      CoachColor theirCoach = theirCard.getCoach();
      if (ourCoach != theirCoach && ourCard.beats(theirCard, direction)) {
        theirCard.setCoach(ourCoach);
        refereeBattlePhase(them);
      }
    }
  }

  public void refereeBattlePhase(GridCellHole us) {
    throw new IllegalStateException("should not directly call a hole cell to battle");
  }

}
