package cs3500.threetrios.model;

public class DefaultReferee implements BattlePhaseReferee {

  @Override
  public void refereeBattlePhase(ABoardCell us) {
    {
      if (!doesCellHaveCard(us)) {
        return;
      }
      for (CardinalDirection direction : CardinalDirection.values()) {
        if (getCellNeighborToThe(direction, us) == null) {
          continue;
        }
        Card ourCard = getCellCard(us);
        Coach ourCoach = getCardCoach(ourCard);
        ABoardCell them = getCellNeighborToThe(direction, us);
        Card theirCard;
        if (doesCellHaveCard(them)) {
          theirCard = getCellCard(them);
        } else {
          return;
        }
        Coach theirCoach = getCardCoach(theirCard);
        if (ourCoach != theirCoach) {
          if (ourCard.beats(theirCard, direction)) {
            theirCard.setCoach(ourCoach);
            them.acceptBattlePhase(this);
          }
        }
      }
    }
  }



}

