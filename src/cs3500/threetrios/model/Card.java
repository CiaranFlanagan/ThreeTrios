package cs3500.threetrios.model;

public interface Card {
  String getName();
  AttackValue getAttackValue(Direction direction);
}
