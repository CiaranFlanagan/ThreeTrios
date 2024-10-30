package cs3500.threetrios.model;

public class DefaultReferee implements BattlePhaseReferee {

  @Override
  public void refereeBattle(ABoardCell ab) {
    ab.acceptBattlePhase(this);
  }

  public void refereeBattle(CardCell us) {
    if (!doesCellHaveCard(us)) {
      return;
    }
    for (CardinalDirection direction : CardinalDirection.values()) {
      if (getCellNeighborToThe(direction, us) == null) {
        continue;
      }
      Card ourCard = getCellCard(us);
      ABoardCell them = getCellNeighborToThe(direction, us);
      Card theirCard;
      if (doesCellHaveCard(them)) {
        theirCard = getCellCard(them);
      } else {
        return;
      }
      System.out.println("we have a fight with: " + theirCard);
      if (getCardCoach(ourCard) != getCardCoach(theirCard)) {
        System.out.println("we dispatch correctly");
        System.out.println(them instanceof CardCell);
        refereeBattleCascadeOnLose(them, us, direction.opposite());
        return;
      }
    }
  }

  private void refereeBattleCascadeOnLose(ABoardCell us, CardCell them,
                                          CardinalDirection direction) {
    throw new IllegalArgumentException("not implemented");
  }

  private void refereeBattleCascadeOnLose(CardCell us, CardCell them,
                                          CardinalDirection direction) {
    System.out.println("we get here2");
    if (!doesCellHaveCard(us)) {
      return;
    }
    Card ourCard = getCellCard(us);
    Card theirCard = getCellCard(them);
    if (theirCard.beats(ourCard, direction)) {
      Coach theirCoach = getCardCoach(theirCard);
      setCardCoach(ourCard, theirCoach);
      refereeBattle(us);
    }
  }

  public void refereeBattle(HoleCell ab) {
    throw new IllegalStateException("Hole cells should not be the ones who instigate battle" +
            "in default referee's game");
  }

}

