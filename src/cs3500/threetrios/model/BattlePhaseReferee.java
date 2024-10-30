package cs3500.threetrios.model;

public interface BattlePhaseReferee {
  void refereeBattlePhase(ABoardCell ab);

  default ABoardCell getCellNeighborToThe(CardinalDirection direction, ABoardCell ab) {
    return ab.getNeighborToThe(direction);
  }

  default boolean doesCellHaveCard(ABoardCell ab) {
    return ab.hasCard();
  }

  default Card getCellCard(ABoardCell ab) {
    return ab.getCard();
  }

  default void setCardAttackValueInDirection(Card card, AttackValue av, CardinalDirection cd) {
    card.setAttackValueInDirection(av, cd);
  }

  default Coach getCardCoach(Card card) {
    return card.getCoach();
  }

  default void setCardCoach(Card card, Coach newOwner) {
    card.setCoach(newOwner);
  }
}
