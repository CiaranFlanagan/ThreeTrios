package model;

public class SettableAdaptedModel extends ModelAdapter {

  public SettableAdaptedModel set(ModelReadOnly model) {
    this.model = model;
    return this;
  }

}
