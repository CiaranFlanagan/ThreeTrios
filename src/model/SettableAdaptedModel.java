package model;

/**
 * To represent a provider model that can be st with our model.
 */
public class SettableAdaptedModel extends ModelAdapter {

  public SettableAdaptedModel set(ModelReadOnly model) {
    this.model = model;
    return this;
  }

}
